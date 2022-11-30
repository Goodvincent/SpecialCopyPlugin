# SpecialCopyPlugin

## Usage
Download and install the plugin manually from the IntelliJ Plugin Website or use the Plugin Repository Browser in your IDE.  
After install the plugin you can touch a *.copy file template in any folder.  
The copy file like this :
~~~
;SpecialCopy definition
;<LINE_1> for first line
;<LINE_*> for all other lines
;<LINE_N> for last line

StringBuffer sql = new StringBuffer();
sql.append("<line_1>");
sql.append("<line_*>");
sql.append("<line_n>");
~~~
Then you can open the idea settings to set the template file location.  
![img.png](img.png)
After all settings is done and select some words, you can see the menu items in Tools|Special Copy, like this:  
![img_1.png](img_1.png)
When you click the StringBuffer action the result will replace your System Clipboard, then paste the string you can get what you want.
![img_2.png](img_2.png)