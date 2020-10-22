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

  </head>
  <body>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12" align="center">
                 <img src="images/logoSpotify.jpg" align="center" width="150" />
                </div>
            </div>    
            <div class="row">
		<div class="col-md-12">           
			<h4 class="text-center">
                            Detalhes da Playlist!
			</h4>
		</div>
            </div>
                        
                <div class="row" id="conteudo" >
                    <div class="col-md-2">
                        &nbsp;
                    </div>
                    <div class="col-md-2 btn botao" >
                        <span class="text-center"> <a href="novamusica">Upload Musica</a> </span>
                    </div>
                    <div class="col-md-2 btn botao">
                        <span class="text-center"> <a href="playlists">Minhas Playlists</a> </span>
                    </div>
                    <div class="col-md-2 btn botao">
                        <span class="text-center"> <a href="novaplaylist">Nova Playlist</a> </span>
                    </div>
                    <div class="col-md-2 btn botao">
                        <span class="text-center"> <a href="logout">Logout</a> </span>
                    </div>
                    
                </div>   
                <div class="col-md-2">
                    &nbsp;
                </div>
                
        </div>
        <div class="row">
            <div class="col-md-2">&nbsp;</div>
            <div class="col-md-8">
                <h4>${PlayList.titulo} <a href="player"> <img id="imgplay" src="images/player.png" alt="Tocar Playlist" title="Tocar Playlist"> </a> </h4>
            </div>
            <div class="col-md-2">&nbsp;</div>
        </div>
        <div class="row">
            <div class="col-md-2">&nbsp;</div>
            <div class="col-md-8">
                <h5> <a href="./recuperamusicas?idplaylist=${PlayList.id}"> + Adicionar M&uacute;sicas </a> </h5>
            </div>
            <div class="col-md-2">&nbsp;</div>
        </div>    
      
            <c:forEach var="Musica" items="${PlayList.musicas}">
                    <div class="row">
                        <div class="col-md-2">&nbsp;</div>
                        <div class="col-md-8">
                            <span class="tituloMusica">
                                ${Musica.titulo}
                            </span>
                            <span class="artista">
                                ${Musica.artista} (Album: ${Musica.album})
                            </span>
                        </div>
                        <div class="col-md-2">&nbsp;</div>
                    </div>
            </c:forEach>       
            
      </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>