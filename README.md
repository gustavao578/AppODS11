# Funções de Usuários:
cadastrarUsuarioComum: Adiciona usuários comuns ao banco de dados.
```java
public void cadastrarUsuarioComum(String nome, String email, String senha, String endereco)
```
ex:
```java
cadastrarUsuarioComum("João Silva", "joao@email.com", "123senha", "Rua Principal, 123");
```
cadastrarGerente: Adiciona usuários com permissão de administrador.
```java
public void cadastrarGerente(String nome, String email, String senha, String endereco, String tipo)
```
ex:
```java
cadastrarGerente("Maria Souza", "maria@email.com", "senha123", "Avenida Central, 456", "Admin");
```
listarUsuarios: Lista todos os usuários registrados.
```java
public List<Usuario> listarUsuarios()
```
Ex:
```java
List<Usuario> usuarios = listarUsuarios();
for(Usuario usuario : usuarios) {
    System.out.println(usuario.getNome());
}
```
removerUsuario: Remove um usuário pelo email.
```java
public boolean removerUsuario(String email)
```
ex:
```java
boolean sucesso = removerUsuario("joao@email.com");
if(sucesso) {
    System.out.println("Usuário removido com sucesso.");
} else {
    System.out.println("Erro ao remover usuário.");
}
```
editarUsuario: Edita as informações de um usuário.
```java
public String editarUsuario(String emailAtual, String novoEmail, String novoNome, String novoEndereco)
```
ex:
```java
String resultado = editarUsuario("joao@email.com", "novojoao@email.com", "João de Souza", "Rua Nova, 789");
System.out.println(resultado);
```
trocarTipoUsuario: Altera o tipo de um usuário entre "comum" e "gerente".
```java
public boolean trocarTipoUsuario(String email)
```
ex:
```java
boolean sucesso = trocarTipoUsuario("maria@email.com");
if(sucesso) {
    System.out.println("Tipo de usuário alterado com sucesso.");
} else {
    System.out.println("Erro ao alterar tipo de usuário.");
}
```
validarUsuario: Valida o login de um usuário pelo email e senha.
```java
public String validarUsuario(String email, String senha)
```
ex:
```java
String status = validarUsuario("joao@email.com", "123senha");
if(status.equals("Valido")) {
    System.out.println("Login realizado com sucesso.");
} else {
    System.out.println("Credenciais inválidas.");
}
```
obterUsuarioPorEmail: Obtém os detalhes de um usuário pelo email.
```java
public Usuario obterUsuarioPorEmail(String email)
```
ex:
```java
Usuario usuario = obterUsuarioPorEmail("joao@email.com");
System.out.println("Usuário encontrado: " + usuario.getNome());
```
# Funções de Ônibus
adicionarLinhaOnibus: Adiciona uma linha de ônibus ao banco de dados.
```java
public void adicionarLinhaOnibus(String linha, String partida, String destino)
```
ex:
```java
adicionarLinhaOnibus("500", "Centro", "Bairro A");
```
removerLinhaOnibus: Remove uma linha de ônibus pelo nome da linha.
```java
public void removerLinhaOnibus(String linha)
```
ex:
```java
removerLinhaOnibus("500");
```
buscarLinhasOnibus: Busca linhas de ônibus por partida e destino.
```java
public List<LinhaOnibus> buscarLinhasOnibus(String partida, String destino) 
```
ex:
```java
List<LinhaOnibus> linhas = buscarLinhasOnibus("Centro", "Bairro A");
for(LinhaOnibus linha : linhas) {
    System.out.println(linha.getNumero());
}
```
# Funções de Sugestões:
adicionarSugestao: Adiciona uma sugestão ao banco de dados.
```java
public void adicionarSugestao(String descricao, String usuario)
```
ex:
```java
String emailUsuario = UsuarioLogado.getUsuario().getEmail();
bancoDeDados.adicionarSugestao(descricao, emailUsuario);
```
removerSugestao: Remove uma sugestão pelo conteúdo da descrição.
```java
public boolean removerSugestao(String descricao)
```
ex:
```java
boolean removida = bancoDeDados.removerSugestao("Melhorar a sinalização na estação central");
if (removida) {
    System.out.println("Sugestão removida com sucesso!");
} else {
    System.out.println("Sugestão não encontrada.");
}
```
listarSugestoes: Lista todas as sugestões registradas.
```java
public List<Sugestao> listarSugestoes()
```
ex:
```java
List<Sugestao> sugestoes = bancoDeDados.listarSugestoes();
for (Sugestao s : sugestoes) {
    System.out.println("Descrição: " + s.getDescricao() + ", Usuário: " + s.getUsuario());
}
```
listarSugestoesPorUsuario: Lista sugestões feitas por um usuário específico.
```java
public List<Sugestao> listarSugestoesPorUsuario(String usuarioEmail)
```
ex:
```java
List<Sugestao> sugestoes = bancoDeDados.listarSugestoesPorUsuario("usuario@example.com");
for (Sugestao s : sugestoes) {
    System.out.println("Descrição: " + s.getDescricao());
}
```
