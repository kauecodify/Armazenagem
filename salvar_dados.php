<?php
//método POST
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // recupera os dados enviados pelo aplicativo Java
    $dataPagamento = $_POST['dataPagamento'];
    $empresa = $_POST['empresa'];
    $valor = $_POST['valor'];

    // cria uma string com os dados formatados
    $dadosFormatados = "Data de Pagamento: $dataPagamento\nEmpresa: $empresa\nValor: $valor\n\n";

    // define o caminho do arquivo onde os dados serão salvos
    $caminhoArquivo = "dados_salvos.txt";

    // tenta abrir o arquivo para escrita
    if ($arquivo = fopen($caminhoArquivo, "a")) {
        // escreve os dados no arquivo
        fwrite($arquivo, $dadosFormatados);
        fclose($arquivo); // fecha o arquivo após a escrita

        // retorna uma mensagem de sucesso para o aplicativo Java
        echo "Dados salvos com sucesso!";
    } else {
        // se não for possível abrir o arquivo, retorna uma mensagem de erro para o aplicativo Java
        echo "Erro ao salvar os dados.";
    }
} else {
    // se a requisição não for via método POST, retorna uma mensagem de erro para o aplicativo Java
    echo "Método inválido.";
}
?>
