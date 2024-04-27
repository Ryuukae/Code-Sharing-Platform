<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <script>
        function send() {
            let object = {
                "code": document.getElementById("code_snippet").value,
                "time": document.getElementById("time_restriction_field").value,
                "views": document.getElementById("views_restriction_field").value
            };

            let json = JSON.stringify(object);

            let xhr = new XMLHttpRequest();
            xhr.open("POST", '/api/code/new', false)
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(json);

            if (xhr.status === 200) {
                alert("Success!");
            }
        }
    </script>
    <style>
        body {
            background: #222;
        }

        .restrictions-container {
            display: flex;
            flex-direction: column;
            justify-content: space-around;
        }

        .restrictions-text {
            background: #222;
            color: #FF6E6E;
            font-family: Baskerville, serif;
            font-size: 14px;
            text-align: left;
            font-weight: 100;
            display: inline-block;
            margin: 0;
        }

        .button {
            background: #333;
            color: #FF6E6E;
            font-family: Baskerville, serif;
            font-size: 14px;
            text-align: center;
            font-weight: 100;
            display: inline-block;
            margin-left: 330px;


        }

        .restrictions-textarea {
            background: #333;
            color: #FF6E6E;
            font-family: Baskerville, serif;
            font-size: 14px;
            text-align: left;
            font-weight: 100;
            display: inline-block;
            padding: 2px;
            margin: 0;
            width: 275px;
        }

        .code-text {
            font-family: Baskerville, serif;
            font-size: 14px;
            text-align: left;
            font-weight: 100;
            width: 20%;
            height: 70px;
            background: #333;
            color: #FF6E6E;
            border: 1px solid #FFF;
            display: inline-block; /* Add this line */
            padding: 2px;
            margin: 0;

        }


    </style>
</head>
<body>
<div class="restrictions-container">
    <p><textarea id="code_snippet" class="code-text">// write your code here</textarea></p>
    <p><label id="time_restriction_label" for="time_restriction_field" class="restrictions-text">Time restriction: </label>
        <input type="number" id="time_restriction_field" class="restrictions-textarea"></p>
    <p><label id="views_restriction_label" for="views_restriction_field" class="restrictions-text">Views restriction: </label>
        <input type="number" id="views_restriction_field" class="restrictions-textarea"></p>
</div>
<br>
<button id="send_snippet" type="submit" onclick="send()" class="button">Submit</button>
</body>
</html>
