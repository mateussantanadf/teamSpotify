
<!DOCTYPE html>
<jsp:useBean id="Usuario" type="br.com.mateus.teamspotify.model.Usuario" scope="session"/>
<jsp:useBean id="PlayList" type="br.com.mateus.teamspotify.model.PlayList" scope="session"/>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html lang="en">
    <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">

      <title>Spotify, from Mateus!</title>

      <meta name="description" content="Source code generated using layoutit.com">
      <meta name="author" content="LayoutIt!">

      <link href="css/bootstrap.min.css" rel="stylesheet">
      <link href="css/style.css" rel="stylesheet">
      
      <script type="text/javascript">
          var musics = new Array();
          var repeat = false;
          var currentSong = 0;
          var totalMusicas = 0;
          var URL = "http://localhost:8080/";
          var player;
          
          function setupPlayer(){
              var divMusicas = document.getElementById("playerContent");
              var filhos = divMusicas.childNodes;
              for (i=0; i<filhos.length; i++){
                  if (filhos[i].nodeName === "DIV"){
                      musics.push(filhos[i].id);
                      totalMusicas++;
                  }
              }
              console.log(musics);
              player = document.getElementById("musicplayer");
              player.src = URL+musics[0];
              document.getElementById("nowPlaying").innerHTML="Now Playing:" + document.getElementById(musics[currentSong]).innerHTML;
              
              player.onended = function(){  
                  if (currentSong < musics.length-1){
                      currentSong = currentSong+1;
                      player.src = URL+musics[currentSong];
                      player.play();
                  }
                  else{
                      if (repeat){
                          currentSong = 0;
                          player.src = URL+musics[currentSong];
                          player.play();
                      }
                      else{
                          alert("Fim das musicas");
                      }
                  }
              };
              
              document.getElementById("nowPlaying").innerHTML="Now Playing:" + document.getElementById(musics[currentSong]).innerHTML;
          }
          function changeRepeat(){
              repeat = !repeat;
              if (repeat){
                  document.getElementById("imgRepeat").src="images/repeat_green.png";
              }
              else{
                  document.getElementById("imgRepeat").src="images/repeat_gray.png";
              }
          }
          function play(objetoMusica){
              console.log(objetoMusica.id);
              for (i=0; i<musics.length; i++){
                  if (musics[i] === objetoMusica.id){
                      currentSong=i;
                      
                      player.src=URL+musics[currentSong];
                      player.play();
                      document.getElementById("nowPlaying").innerHTML="Now Playing:" + document.getElementById(musics[currentSong]).innerHTML;
                  }
              }
          }
      </script>

    </head>
    <body onload="setupPlayer()">
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12" align="center">
                    <a href="playlists"><img src="images/logoSpotify.jpg" align="center" width="150" /></a>
                    <img id="imgRepeat" src="images/repeat_gray.png" class="rounded mx-auto d-block" width="40" onclick="changeRepeat();" align="center">
                </div>
            </div>   
        </div>
        <div id="playerContent">
            <c:forEach var="music" items="${PlayList.musicas}">
                <div id="${music.linkMP3}" class="musica" onclick="play(this);">
                    ${music.titulo} (${music.artista})
                </div>
            </c:forEach>
        </div>
        
        <div id="playerdiv">
            <div id="nowPlaying"> Now Playing: </div>
            <audio id="musicplayer" controls controlsList="nodownload" src=""/>
        </div>
        
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>    