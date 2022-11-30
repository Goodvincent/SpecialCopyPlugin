# SpecialCopyPlugin

## Usage
Download and install the plugin manually from the IntelliJ Plugin Website or use the Plugin Repository Browser in your IDE.  
After install the plugin you can touch a *.copy file template in any folder.  
The copy file like this :
~~~
;SpecialCopy definition<br>
;&lt;LINE_1&gt; for first line<br>
;&lt;LINE_*&gt; for all other lines<br>
;&lt;LINE_N&gt; for last line<br>

StringBuffer sql = new StringBuffer();<br>
sql.append("&lt;line_1&gt;");<br>
sql.append("&lt;line_*&gt;");<br>
sql.append("&lt;line_n&gt;");<br>
~~~
Then you can open the idea settings to set the template file location.  
![img.png](img.png)
After all settings is done and select some words, you can see the menu items in Tools|Special Copy, like this:  
![img_1.png](img_1.png)
When you click the StringBuffer action the result will replace your System Clipboard, then paste the string you can get what you want.
![img_2.png](img_2.png)