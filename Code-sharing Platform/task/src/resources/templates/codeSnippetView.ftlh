<!DOCTYPE html>
<html lang="us">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <style>
        body {
            background: #222;
        }

        .code-text {
            font-family: Baskerville, serif;
            font-size: 13px;
            text-align: left;
            font-weight: 100;
            background: #333;
            color: #FF6E6E;
            border: 1px solid #FFF;
            display: inline-block; /* Add this line */
            padding: 2px;
            margin: 1px;

        }

        .date-text {
            font-weight: 100;
            padding: 1px;
            color: silver;
            background: #333;
            border: 1px solid #FF6E6E;
            width: 20%;
            height: 20px;
            font-style: italic;
            font-size: 13px;
            text-align: center;
            margin: 1px;
        }

    </style>
</head>
<body>
<span id="load_date" class="date-text">${timestamp}</span>
<br>
<pre id="code_snippet"><code class="code-text">${code}</code></pre>
</body>
</html>
