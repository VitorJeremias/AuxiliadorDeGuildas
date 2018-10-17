package modelo;

import utils.StringConstants;

public enum EnumImagens {

	//@formatter:off
	
	//CENTRALIZADO_1
	AUXILIAR(StringConstants.pathPrincipal + "auxiliar.png", EnumParametrosImagem.CENTRALIZADO_1, "Auxiliar"),
	ENTRAR(StringConstants.pathAuxiliadorDeGuildas + "entrar.png", EnumParametrosImagem.CENTRALIZADO_1, "entrar"),
	FECHAR_JANELA_RAPIDO(StringConstants.pathPrincipal + "fecharJanela.png", EnumParametrosImagem.CENTRALIZADO_1, "FecharJanela"),
	OK_RECOMPENSA(StringConstants.pathPrincipal + "okRecompensa.png", EnumParametrosImagem.CENTRALIZADO_1, "okRecompensa"),
	ULTIMA_POSICAO(StringConstants.pathAuxiliadorDeGuildas + "ultimaPosicao.png", EnumParametrosImagem.CENTRALIZADO_1, "ultimaPosicao"),
	VOLTAR_PAGINACAO(StringConstants.pathAuxiliadorDeGuildas + "voltarPaginacao.png", EnumParametrosImagem.CENTRALIZADO_1, "voltarPaginacao"),
	NUMERO_UM(StringConstants.pathAuxiliadorDeGuildas + "numeroUm.png", EnumParametrosImagem.CENTRALIZADO_1, "numeroUm"),
	MEMBROS(StringConstants.pathAuxiliadorDeGuildas + "membros.png", EnumParametrosImagem.CENTRALIZADO_1, "membros"),
	DESCER_MEMBROS(StringConstants.pathAuxiliadorDeGuildas + "descerMembros.png", EnumParametrosImagem.CENTRALIZADO_1, "descerMembros"),
	//DEIXAR_GUILDA(StringConstants.pathAuxiliadorDeGuildas + "deixarGuilda.png", EnumParametrosImagem.CENTRALIZADO_1, "deixarGuilda"),
	DEIXAR_GUILDA_PAR(StringConstants.pathAuxiliadorDeGuildas + "deixarGuildaPar.png", EnumParametrosImagem.CENTRALIZADO_1, "deixarGuildaPar"),
	DEIXAR_GUILDA_IMPAR(StringConstants.pathAuxiliadorDeGuildas + "deixarGuildaImpar.png", EnumParametrosImagem.CENTRALIZADO_1, "deixarGuildaImpar"),
	DEIXAR(StringConstants.pathAuxiliadorDeGuildas + "deixar.png", EnumParametrosImagem.CENTRALIZADO_1, "deixar"),
	
	//CENTRALIZADO_10
	FECHAR_JANELA(StringConstants.pathPrincipal + "fecharJanela.png", EnumParametrosImagem.CENTRALIZADO_10, "FecharJanela"),	
	OK(StringConstants.pathAuxiliadorDeGuildas + "ok.png", EnumParametrosImagem.CENTRALIZADO_10, "OK");
	//@formatter:on

	private String path;
	private EnumParametrosImagem parametrosDaImagem;
	private String acao;

	public String getPath() {
		return path;
	}

	public EnumParametrosImagem getParametrosDaImagem() {
		return parametrosDaImagem;
	}

	public String getAcao() {
		return acao;
	}

	EnumImagens(String path, EnumParametrosImagem parametrosImagem, String acao) {
		this.path = path;
		this.parametrosDaImagem = parametrosImagem;
		this.acao = acao;
	}
}
