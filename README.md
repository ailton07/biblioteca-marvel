Biblioteca Marvel
===================

Aplicação Android que consome a Marvel API (disponível  <i class="icon-link"></i>[aqui](http://developer.marvel.com/) )


##Funcionalidades

###Listagem de Heróis

Nessa view, é apresentada uma listagem de heróis carregados via API (20 primeiros heróis, em ordem alfabética) .

###Busca por Heróis

Quando pressionado o menu da lupa na toolbar , é  apresentada uma busca por nome do super-herói. A busca é local, envolvendo os heróis já carregados.

###Detalhes do Herói

Ao selecionar um herói , é apresentada uma view contendo os detalhes desse herói, mostrando informações como nome, foto expandida e descrição. Além dessas informações, são carregadas as imagens das edições dos quadrinhos em que o herói aparece (até 10 imagens). Essas imagens podem ser expandidas ao serem tocadas.

----------

Setup e Build
-------------

É necessário se cadastrar no [link](https://secure.marvel.com/user/register) para se obter as chaves pública e privada que devem ser inseridas no projeto, em:

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