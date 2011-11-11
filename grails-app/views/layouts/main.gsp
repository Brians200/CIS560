<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Stocks and Stuff" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <g:layoutHead />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <g:loginHeader/>
        <g:layoutBody />
    </body>
</html>