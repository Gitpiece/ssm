@echo off
rem author thinkgem@163.com
echo Compressor JS and CSS?
pause
cd %~dp0

call compressor\compressor.bat src\main\webapp\static\nut
rem call compressor\compressor.bat bootstrap\bsie\js\bootstrap-ie.js
rem call compressor\compressor.bat common
rem call compressor\compressor.bat jquery-plugin
rem call compressor\compressor.bat jquery-select2\3.4\select2.css
rem call compressor\compressor.bat jquery-select2\3.4\select2.js
rem call compressor\compressor.bat jquery-jbox\2.3\Skins\Bootstrap\jbox.css
rem call compressor\compressor.bat jquery-jbox\2.3\jquery.jBox-2.3.js
rem call compressor\compressor.bat jquery-validation\1.11.0\jquery.validate.css
rem call compressor\compressor.bat jquery-validation\1.11.0\jquery.validate.js
rem call compressor\compressor.bat jquery-ztree\3.5.12\css\zTreeStyle\zTreeStyle.css
rem call compressor\compressor.bat jerichotab\css\jquery.jerichotab.css
rem call compressor\compressor.bat jerichotab\js\jquery.jerichotab.js
rem call compressor\compressor.bat treeTable\themes\vsStyle\treeTable.css
rem call compressor\compressor.bat treeTable\jquery.treeTable.js
rem call compressor\compressor.bat supcan\supcan.js
rem call compressor\compressor.bat modules

echo.
echo Compressor Success
pause
echo on