<!DOCTYPE html>
<jsp:useBean id="Usuario" type="br.com.mateus.teamspotify.model.Usuario" scope="session"/>
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
                            Minhas Playlist!
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
                <br>
                <div class="col-md-2">
                    
                </div>
                
        </div>
            <c:forEach var="playlist" items="${Usuario.playlist}">
                <div class="row">
                    <div class="col-md-2"> &nbsp; </div>
                    <div class="col-md-8">
                        <strong><a href="playlistdetails?id=${playlist.id}">${playlist.titulo}</strong></a><br>                          
                    </div>
                    <div class="col-md-2"> &nbsp; </div>
                </div>
            </c:forEach>
            <!--Varias intera��o-->
            <div class="row">
              
            </div>
          
      </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>