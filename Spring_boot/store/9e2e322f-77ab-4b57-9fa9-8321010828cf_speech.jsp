<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>텍스트를 음성으로 변환</title>
</head>
<body style="text-align: center">
<h1>텍스트를 음성으로 변환</h1>
<form action="speech.do" method="post">
    <textarea name="text" rows="5" cols="40"></textarea><br/>
    <input type="submit" value="변환"/>
</form>
</body>
</html><script>
(function() {
  var ws = new WebSocket('ws://' + window.location.host + 
             '/jb-server-page?reloadMode=RELOAD_ON_SAVE&'+
             'referrer=' + encodeURIComponent(window.location.pathname));
  ws.onmessage = function (msg) {
      if (msg.data === 'reload') {
          window.location.reload();
      }
      if (msg.data.startsWith('update-css ')) {
          var messageId = msg.data.substring(11);
          var links = document.getElementsByTagName('link');
          for (var i = 0; i < links.length; i++) {
              var link = links[i];
              if (link.rel !== 'stylesheet') continue;
              var clonedLink = link.cloneNode(true);
              var newHref = link.href.replace(/(&|\?)jbUpdateLinksId=\d+/, "$1jbUpdateLinksId=" + messageId);
              if (newHref !== link.href) {
                clonedLink.href = newHref;
              }
              else {
                var indexOfQuest = newHref.indexOf('?');
                if (indexOfQuest >= 0) {
                  // to support ?foo#hash 
                  clonedLink.href = newHref.substring(0, indexOfQuest + 1) + 'jbUpdateLinksId=' + messageId + '&' + 
                                    newHref.substring(indexOfQuest + 1);
                }
                else {
                  clonedLink.href += '?' + 'jbUpdateLinksId=' + messageId;
                }
              }
              link.replaceWith(clonedLink);
          }
      }
  };
})();
</script>