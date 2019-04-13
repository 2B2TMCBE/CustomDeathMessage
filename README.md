# CustomDeathMessage
This is the open source version of the custom death message plugin that is used by our server, 2b2tmcpe.org, in the plugin, you can edit every single death message from the config.yml
# Warning
Please do not remove any of the tags from the config.yml, I haven't added a null check, so it might error out if you remove a tag. However, you are free to move around the tags to different location of the sentences.
# Coding Style
[![Coding Style](https://img.shields.io/badge/Coding%20Style-Google%20Style-green.svg)](https://google.github.io/styleguide/javaguide.html)
# API/Framework Used
- Nukkitx Engine [![Nukkitx](https://img.shields.io/badge/Software-Nukkitx-brightgreen.svg)](https://github.com/Nukkitx/Nukkit)
- Raknet Networking Engine
# Installation
- This installation guide is for Debian linux only, windows please download the newest jar file from https://github.com/2B2TMCBE/CustomDeathMessage/releases and then proceed to step 7.

1. require installation of Nukkitx, please go to https://www.nukkitx.com/ for instruction
2. Download maven using this command:
```sudo apt-get install maven```
3. After installation, run this command to clone this git repository:
```git clone https://github.com/2B2TMCBE/CustomDeathMessage.git```
4. Change directory to the plugin's base directory, which contains the file named pom.xml
5. Run this command to compile the plugin using maven
```mvn clean package```
6. Wait until the compiling process is completed, a new directory named `target` should now be created, nevigate into target.
7. move the jar file named `CustomDeathMessage-1.0.0-SNAPSHOT.jar` into Nukkit's server directory, move it into a directory named plugins.
8. Restart the server, the plugin should be installed.
# Contribution
You are free to contribute to this project by sending a pull request, but please make sure you use the same coding style as our plugin. The coding Style guide can by found here: https://google.github.io/styleguide/javaguide.html
# License
[MIT](https://choosealicense.com/licenses/mit/)
