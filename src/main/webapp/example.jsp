<%@ page import="net.golovach.quiz.entity.Question" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <%@ include file="WEB-INF/jspf/cssInclude.jspf"%>
</head>
<body>

<jsp:include page="WEB-INF/jspf/cssInclude.jspf"/>

<br/>

<div class="container">
    <h1>link-icons examples</h1>
    <hr>
    <h2 class="alt">This sample page demonstrates the <em>link-icons</em> plugin.</h2>
    <hr>
    <div class="span-24 last">
        <table>
            <thead>
            <tr>
                <th>Sample link</th>
                <th>Description</th>
                <th>Regular expression of <code>href</code> anchor attribute</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><a href="http://example.com">An external link</a></td>
                <td>Links that starts with <code>http://</code>.</td>
                <td><code>http://.*</code></td>
            </tr>
            <tr>
                <td><a href="https://example.com">A secure external link</a></td>
                <td>Links that starts with <code>https://</code>.</td>
                <td><code>https://.*</code></td>
            </tr>
            <tr>
                <td><a href="mailto:person@example.com">Mail someone</a></td>
                <td>Links that starts with <code>mailto:</code>.</td>
                <td><code>mailto:.*</code></td>
            </tr>
            <tr>
                <td><a href="http://example.com/document.pdf">A PDF document</a></td>
                <td>Links that ends with <code>.pdf</code>.</td>
                <td><code>.*\.pdf</code></td>
            </tr>
            <tr>
                <td><a href="http://example.com/document.doc">A word document</a></td>
                <td>Links that ends with <code>.doc</code>.</td>
                <td><code>.*\.doc</code></td>
            </tr>
            <tr>
                <td><a href="http://example.com/workbook.xls">A Microsoft Excel workbook</a></td>
                <td>Links that ends with <code>.xls</code>.</td>
                <td><code>.*\.xls</code></td>
            </tr>
            <tr>
                <td><a href="http://example.com/feed.rss">An RSS feed</a></td>
                <td>Links that ends with <code>.rss</code>.</td>
                <td><code>.*\.rss</code></td>
            </tr>
            <tr>
                <td><a href="http://example.com/feed.rdf">An RDF feed</a></td>
                <td>Links that ends with <code>.rdf</code>.</td>
                <td><code>.*\.rdf</code></td>
            </tr>
            <tr>
                <td><a href="aim:addbuddy?screenname=example">An AIM instant messaging link</a></td>
                <td>Links that starts with <code>aim:</code>.</td>
                <td><code>aim:.*</code></td>
            </tr>
            </tbody>
        </table>
    </div>
    <hr>
</div>

</body>
</html>
