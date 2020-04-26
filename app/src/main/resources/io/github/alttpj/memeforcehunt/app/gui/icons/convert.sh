#!/bin/bash
set -euo pipefail

for file in *.svg; do
  basename="${file%.*}"
  pngname="${basename}-128.png"
  echo "Converting $file to $pngname"
  inkscape -z -w 128 -h 128 "$file" -e "$pngname" 2>/dev/null
  optipng -o7 "$pngname" 2>&1 | grep "\% decr" || echo "no compresion."
  echo
done

