# SpecialCopyPlugin

eg:
~~~
;SpecialCopy definition<br>
;&lt;LINE_1&gt; for first line<br>
;&lt;LINE_*&gt; for all other lines<br>
;&lt;LINE_N&gt; for last line<br>
StringBuffer sql = new StringBuffer();<br>
sql.append("&lt;line_1&gt;");<br>
sql.append("&lt;line_*&gt;");<br>
sql.append("&lt;line_n&gt;");<br>
