<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $dataPagaento $_POST['dataPagamento'];
    $empresa = $_POST['empresa'];
    $valor = $_POST['valor'];

    $dados = array(
        "dataPagamento" => $dataPagamento,
        "empresa" => $empresa,
        "valor" => $valor
    );

    $json = json_encode($dados);

    echo $json;

    $dadosFormatados = "Data de Pagamento: $dataPagamento\nEmpresa: $empresa\nValor: $valor\n\n";

    $caminhoArquivo = "dados_salvos.txt";

    if ($arquivo = fopen($caminhoArquivo, "a")) {
        fwrite($arquivo, $dadosFormatados);
        fclose($arquivo);
        echo "Dados salvos com sucesso!";
    } else {
        echo "Erro ao salvar os dados.";
}
} else {
    echo "Método inválido.";
}
?>
