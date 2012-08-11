Rebar
=====

Rebar is a plugin framework first [described on sk89q.com](http://www.sk89q.com/2011/10/how-i-stay-sane-while-updating-my-minecraft-server/).
As of writing, the contents if this repository is currently only a minor part
of the entire framework.

The framework is currently to be rolled into WorldEdit. Do not shade it into
any other package as this may cause problematic versioning issues.

Compiling
---------

You need to have Maven installed (http://maven.apache.org). Once installed,
simply run:

    mvn clean package
    
Maven will automatically download dependencies for you. Note: For that to work,
be sure to add Maven to your "PATH".


License
-------

This software is licensed under the GNU General Public License, version 3
(see LICENSE-LGPL3.txt).

Contributions by third parties must be dual licensed under 
GNU General Public License v3 (LICENSE-LGPL3.txt) and the 3-clause BSD license
(LICENSE-BSD.txt).
