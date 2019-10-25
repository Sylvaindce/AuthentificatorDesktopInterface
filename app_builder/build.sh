# convert png to icns
# sips -s format icns AppIcon.png --out AppIcon.icns

echo "Build Windows app"
java -jar packr.jar adi_pack_windows_config.json
echo "Build Macintosh app"
java -jar packr.jar adi_pack_macintosh_config.json
