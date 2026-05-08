# ponderada-base-m10-01
Atividade Ponderada 1

O projeto foi aberto no Android Studio a partir do repositório base fornecido. Para rodar, conectamos um dispositivo ou iniciamos um emulador e utilizamos a opção Run no Android Studio. Inicialmente tentamos executar nos computadores do Inteli, mas o carregamento era muito lento. Migramos para o Mac Mini, onde encontramos um problema de incompatibilidade de versão no `build.gradle` que nos impediu de sincronizar o projeto. Após ajustar a versão para a compatível com o ambiente, a sincronização funcionou e conseguimos buildar normalmente.

## Identificação dos erros

Com o projeto rodando, testamos o lançamento do D6 e percebemos que os resultados exibidos nunca chegavam ao valor 6 e às vezes aparecia o valor 0, o que não faz sentido para um dado. Analisando o código, encontramos o problema na linha que gerava o número aleatório: estava sendo usado `Random.nextInt(6)`, que produz valores de 0 a 5, excluindo o 6 e incluindo o 0. Além disso, a lista de dados disponíveis na interface continha apenas o D6, sem opção para D10, D20 ou D100.

## Correção dos erros

O bug da geração aleatória foi corrigido substituindo `Random.nextInt(6)` por `(1..6).random()`, que garante que o intervalo começa em 1 e termina em 6, cobrindo todos os valores válidos do dado. Aplicamos a mesma lógica para os demais dados: `(1..10).random()` para o D10, `(1..20).random()` para o D20 e `(1..100).random()` para o D100. A lista de seleção na interface também foi atualizada para incluir todos os quatro tipos, e o bloco `when` do botão foi expandido para tratar cada um deles corretamente.

## Ir além

Para ir além dos requisitos básicos, adicionamos uma representação visual da face do D6 de acordo com o resultado sorteado. Quando o dado selecionado é o D6 e um valor já foi gerado, o aplicativo exibe o emoji correspondente à face do dado, por exemplo, ⚀ para 1, ⚁ para 2, até ⚅ para 6. Isso foi feito com uma função Composable simples que recebe o valor sorteado e mapeia para o emoji correto, exibindo-o em tamanho grande na tela. Para os outros tipos de dado, o resultado continua sendo exibido apenas em texto.

## Dificuldades

A principal dificuldade foi de ambiente: os computadores do Inteli apresentavam lentidão significativa, o que tornava o desenvolvimento inviável. A solução foi migrar para o Mac Mini, mas isso trouxe um novo problema — a versão configurada no `build.gradle` não era compatível com o ambiente do Mac, impedindo a sincronização do projeto. Após identificar e corrigir a versão, o projeto passou a funcionar corretamente. No código em si, a dificuldade foi entender por que o `resultado` precisava ser do tipo `Int?` em vez de `String`, o que gerava erros ao tentar atribuir o valor sorteado diretamente à variável.
