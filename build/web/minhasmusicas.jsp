<!DOCTYPE html>
<jsp:useBean id="Usuario" type="br.com.mateus.teamspotify.model.Usuario" scope="session"/>
<jsp:useBean id="ListaMusicas" type="java.util.List" scope="request"/>
<jsp:useBean id="idPlaylist" type="java.lang.String" scope="request"/>
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
    
    <script>
        function adicionar(idPlaylist, idMusica){
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("GET", "http://localhost:8080/incluirnaplaylist?idplaylist="+idPlaylist+"&idmusica="+idMusica);
        xmlhttp.onreadystatechange = function(){
            if (xmlhttp.status === 200 && xmlhttp.readyState === 4){
                alert(xmlhttp.responseText);
            }
        };
        xmlhttp.send();
        }
    </script>

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
                            Acervo M&aacute;sicas!
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
            <c:forEach var="musica" items="${ListaMusicas}">
                <div class="row">
                    <div class="col-md-2"> &nbsp; </div>
                    <div class="col-md-1">
                        <button class="btn" onclick="adicionar(${idPlaylist}, ${musica.id});"> + </button>                          
                    </div>
                    <div class="col-md-7">
                        ${musica.titulo} (${musica.artista})<br/>
                        <span class="artista"> Album: ${musica.album} <br/></span>
                        <span class="artista"> Estilo:
                            <c:if test="${musica.estilo == 1}"> Rock </c:if>
                            <c:if test="${musica.estilo == 2}"> Sertanejo/Moda de Viola </c:if>
                            <c:if test="${musica.estilo == 3}"> Pagode/Samba </c:if>
                            <c:if test="${musica.estilo == 4}"> Eletronico/Fritando </c:if>
                            <c:if test="${musica.estilo == 5}"> Musicas da Jovem Pan </c:if>
                            <c:if test="${musica.estilo == 6}"> Outros </c:if>
                        </span>
                    </div>
                    <div class="col-md-2"> &nbsp; </div>
                </div>
            </c:forEach>
            <!--Varias interação-->
            <div class="row">
              
            </div>
          
      </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>