package io.github.alttpj.memeforcehunt.gui;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.HORIZONTAL_CENTER;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;


import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;

import io.github.alttpj.memeforcehunt.common.value.Skin;
import io.github.alttpj.memeforcehunt.lib.AlttpRomPatcher;

public class MemeforceHuntApp {

  public static final String VERSION = "2.0.0-SNAPSHOT";
  private static final String LINK = "https://github.com/fatmanspanda/MemeforceHunt/releases";

  private static final int PER_ROW = 8;

  static final Skin[] SKINS = Skin.values();

  public static void main(final String[] args) throws IOException {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          printGUI();
        } catch (final Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public static void printGUI() {
    // try to set LaF
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (final Exception e) {
      // do nothing
    } //end System

    final Dimension d = new Dimension(800, 600);
    final JFrame frame = new JFrame("Memeforce Hunt v" + VERSION);

    final SpringLayout l = new SpringLayout();
    final JPanel wrap = (JPanel) frame.getContentPane();
    wrap.setLayout(l);

    final JLabel skinsText = new JLabel("---");
    final JComboBox<Skin> skins = new JComboBox<Skin>(Skin.values());
    skins.setEditable(false);

    // rom name
    final JTextField fileName = new JTextField("");
    l.putConstraint(NORTH, fileName, 5, NORTH, wrap);
    l.putConstraint(EAST, fileName, -15, HORIZONTAL_CENTER, skinsText);
    l.putConstraint(WEST, fileName, 5, WEST, wrap);
    frame.add(fileName);

    // file chooser
    final JButton find = new JButton("Open");
    l.putConstraint(NORTH, find, 0, NORTH, fileName);
    l.putConstraint(EAST, find, -5, EAST, wrap);
    l.putConstraint(WEST, find, 5, EAST, fileName);
    frame.add(find);

    // skin chooser
    l.putConstraint(NORTH, skinsText, 5, SOUTH, fileName);
    l.putConstraint(EAST, skinsText, -5, EAST, wrap);
    l.putConstraint(WEST, skinsText, 5, HORIZONTAL_CENTER, wrap);
    frame.add(skinsText);

    // patch button
    final JButton go = new JButton("Patch");
    l.putConstraint(NORTH, go, 5, SOUTH, fileName);
    l.putConstraint(EAST, go, -5, HORIZONTAL_CENTER, wrap);
    l.putConstraint(WEST, go, 5, WEST, wrap);
    frame.add(go);

    // preview
    final JLabel prev = new JLabel();
    prev.setHorizontalAlignment(SwingConstants.CENTER);
    l.putConstraint(NORTH, prev, 5, SOUTH, skinsText);
    l.putConstraint(EAST, prev, 0, EAST, skinsText);
    l.putConstraint(WEST, prev, 0, WEST, skinsText);
    frame.add(prev);

    // random patch button
    final JButton rand = new JButton("Surprise me");
    l.putConstraint(NORTH, rand, 5, SOUTH, go);
    l.putConstraint(EAST, rand, 0, EAST, go);
    l.putConstraint(WEST, rand, 0, WEST, go);
    frame.add(rand);

    final JPanel iconList = new JPanel(new GridBagLayout());
    iconList.setBackground(null);
    final GridBagConstraints c = new GridBagConstraints();
    l.putConstraint(NORTH, iconList, 5, SOUTH, prev);
    l.putConstraint(EAST, iconList, 0, EAST, wrap);
    l.putConstraint(WEST, iconList, 0, WEST, wrap);
    frame.add(iconList);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridy = 0;
    c.gridx = 0;
    for (final Skin s : SKINS) {
      final SkinButton sb = new SkinButton(s);
      sb.addActionListener(
          arg0 -> {
            skins.setSelectedItem(sb.getSkin());
          });
      iconList.add(sb, c);
      sb.setToolTipText("Use Skin \"" + s.getName() + "\".");
      c.gridx++;
      if (c.gridx == PER_ROW) {
        c.gridx = 0;
        c.gridy++;
      }
    }

    // update checks
    final JButton update = new JButton("Check for updates");
    l.putConstraint(SOUTH, update, -5, SOUTH, wrap);
    l.putConstraint(EAST, update, -5, EAST, wrap);
    frame.add(update);

    update.addActionListener(
        arg0 -> {
          final URL aa;
          try {
            aa = new URL(LINK);
            final Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
              desktop.browse(aa.toURI());
            }
          } catch (final Exception e) {
            JOptionPane.showMessageDialog(frame,
                "uhhh",
                "Houston, we have a problem.",
                JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
          }
        });

    // file explorer
    final FileDialog explorer = new java.awt.FileDialog((java.awt.Frame) null);
    final FilenameFilter filenameFilter = (dir, name) -> {
      if (!name.endsWith("sfc")) {
        return false;
      }

      return true;
    };
    explorer.setFilenameFilter(filenameFilter);

    skins.addItemListener(
        arg0 -> {
          final Skin sel = (Skin) skins.getSelectedItem();
          prev.setIcon(sel.getImageIcon());
          skinsText.setText(sel.toString());
        });
    prev.setIcon(Skin.BENANA.getImageIcon());
    skinsText.setText(Skin.BENANA.toString());

    // load sprite file
    find.addActionListener(
        arg0 -> {
          //explorer.setSelectedFile(EEE);
          explorer.setMode(FileDialog.LOAD);
          explorer.setVisible(true);

          // read the file
          final String n = explorer.getFile();

          if (n == null) {
            return;
          }

          if (n.endsWith(".sfc")) {
            fileName.setText(n);
          }
        });

    go.addActionListener(
        arg0 -> {
          final String fileNameText = fileName.getText();
          try {
            AlttpRomPatcher.patchROM(fileNameText, (Skin) skins.getSelectedItem());
          } catch (final Exception e) {
            JOptionPane.showMessageDialog(frame,
                "Something went wrong.",
                "PROBLEM",
                JOptionPane.WARNING_MESSAGE,
                Skin.SCREAM.getImageIcon());
            return;
          }
          JOptionPane.showMessageDialog(frame,
              "SUCCESS",
              "Enjoy",
              JOptionPane.PLAIN_MESSAGE,
              Skin.BENANA.getImageIcon());
        });

    rand.addActionListener(
        arg0 -> {
          final String fileNameText = fileName.getText();
          final int r = (int) (Math.random() * SKINS.length);

          try {
            AlttpRomPatcher.patchROM(fileNameText, SKINS[r]);
          } catch (final Exception e) {
            JOptionPane.showMessageDialog(frame,
                "Something went wrong.",
                "PROBLEM",
                JOptionPane.WARNING_MESSAGE,
                Skin.SCREAM.getImageIcon());
            return;
          }
          JOptionPane.showMessageDialog(frame,
              "SUCCESS",
              "Enjoy",
              JOptionPane.PLAIN_MESSAGE,
              Skin.BENANA.getImageIcon());
        });

    // ico
    BufferedImage ico;
    try {
      ico = ImageIO.read(Skin.class.getResourceAsStream("/triforce piece.png"));
    } catch (final IOException e) {
      ico = new BufferedImage(16, 16, BufferedImage.TYPE_4BYTE_ABGR);
    }

    frame.setIconImage(ico);

    // frame setting
    frame.setMinimumSize(d);
    frame.setResizable(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(350, 350);

    frame.setVisible(true);
  }
}
