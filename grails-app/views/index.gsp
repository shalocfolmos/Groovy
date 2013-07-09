<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
		<style type="text/css" media="screen">
            .ui-menu { width: 300px; }

			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
        <g:javascript src="widgetHandle.js"/>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

        <div style="padding-left:30px;box-shadow: 0 0 3px 1px #AAAAAA;background-color: #EFEFEF;height: 40px;padding-top: 20px;">
            <ul style="list-style: none;">
                <li style="display: inline;">
                    <g:remoteLink action="ajaxContent" controller="index" params="[module:'template']" update="displayContent">邮箱模板</g:remoteLink>
                </li>
                <li style="display: inline; padding-left: 10px;">
                    <g:remoteLink action="ajaxContent" controller="index" params="[module:'templateFramework']" update="displayContent">模板框架</g:remoteLink>
                </li>
                <li style="display: inline; padding-left: 10px;"><g:link action="create" controller="templateFramework">创建初始模板</g:link></li>
                <li style="display: inline; padding-left: 10px;"><g:link action="create" controller="template">创建模板</g:link></li>
            </ul>
        </div>
        <div style="padding-top: 20px;" id="displayContent">
        </div>
        <div id="page" style="display: none;">${page}</div>
        <div style="display: none;" id="ajaxLink">
            <g:link action="ajaxContent" controller="index" />
        </div>



        <script type="text/javascript">
                $(document).ready(
                        function() {
                                jQuery.ajax(
                                        {type:'POST',
                                         data:{'module': $("#page").html()},
                                         url:$("#ajaxLink").children()[0].getAttribute("href"),
                                         success:
                                                 function(data,textStatus){
                                                    $('#displayContent').html(data);
                                                 },
                                         error:function(XMLHttpRequest,textStatus,errorThrown){alert("服务器错误,请重新刷新页面")}}
                                );


                        }
                )
            </script>
            <g:javascript library="jquery"/>
            <r:require module="jquery-ui"/>
        </body>
</html>
