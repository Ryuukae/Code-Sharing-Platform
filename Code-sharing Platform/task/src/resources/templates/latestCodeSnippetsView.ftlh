<!DOCTYPE html>
<html lang="en">
<head>
    <title>Latest</title>
    <style>
        .date-text {
            font-weight: 100;
            padding: 1px;
            color: silver;
            background: #333;
            border: 1px solid #FF6E6E;
            width: 6%;
            height: 15px;
            font-style: italic;
            font-size: 12px;
            text-align: center;
        }

        body {
            background: #222;
        }

        .code-text {
            font-family: Baskerville, serif;
            font-size: 13px;
            text-align: left;
            font-weight: 100;
            color: #FF6E6E;
            border: 1px solid #FFF;
            display: inline-block; /* Add this line */
            padding: 2px;
        }
    </style>
</head>
<body>
<div>
    <#list snippetDtos as snippetDto>
        <tr>
            <td>
                <span class="date-text">${snippetDto.timestamp}</span>
            </td>
            <br>
            <td>
                <pre class="code-text"><code>${snippetDto.codeSnippet}</code></pre>
            </td>
        </tr>
        <br><br>
    </#list>
</div>
</body>
</html>
