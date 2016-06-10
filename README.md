Biblioteca Marvel
===================

Aplica��o Android que consome a Marvel API (dispon�vel  <i class="icon-link"></i>[aqui](http://developer.marvel.com/) )


##Funcionalidades

###Listagem de Her�is

Nessa view, � apresentada uma listagem de her�is carregados via API (20 primeiros her�is, em ordem alfab�tica) .

###Busca por Her�is

Quando pressionado o menu da lupa na toolbar , �  apresentada uma busca por nome do super-her�i. A busca � local, envolvendo os her�is j� carregados.

###Detalhes do Her�i

Ao selecionar um her�i , � apresentada uma view contendo os detalhes desse her�i, mostrando informa��es como nome, foto expandida e descri��o. Al�m dessas informa��es, s�o carregadas as imagens das edi��es dos quadrinhos em que o her�i aparece (at� 10 imagens). Essas imagens podem ser expandidas ao serem tocadas.

----------

Setup e Build
-------------

� necess�rio se cadastrar no [link](https://secure.marvel.com/user/register) para se obter as chaves p�blica e privada que devem ser inseridas no projeto, em:

[/app/src/main/java/com/example/ailtonfh/bibliotecamarvel/api/AuthenticationAPI.java](https://github.com/ailton07/biblioteca-marvel/blob/master/app/src/main/java/com/example/ailtonfh/bibliotecamarvel/api/AuthenticationAPI.java)

```javascript
static private String privateKey = "";
static public String publicKey = "";
```

Screenshots
-------------
 
Tela Inicial
 
![Initial Screen](http://i.imgur.com/gscVQCg.png?1)

Busca

![Search Screen](http://i.imgur.com/k76RmEN.png?1)

Tela de detalhes

![Details Screen](http://i.imgur.com/6lSqkXp.png?1)