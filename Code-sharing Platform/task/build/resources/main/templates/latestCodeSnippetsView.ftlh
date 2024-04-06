<!DOCTYPE html>
<html lang="en">
<head>
    <title>Latest</title>
    <style>
        .code-container {
            font-family: Baskerville, serif;
            box-sizing: border-box;
            border: 1px solid silver;
            font-weight: 100;
            font-size: 13px;
            margin: 0;
            width: 80%;
            text-align: left;
            background: #333;
        }

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
        }

    </style>
</head>
<body>
<div>

    <#list snippetDtos as snippetDto>


        <tr>
            <td>
                <pre class="code-text"><code>${snippetDto.code}</code></pre>
            </td>
            <td><span class="date-text">${snippetDto.timestamp}</span></td>
        </tr>


    </#list>
</div>

</body>
</html>
