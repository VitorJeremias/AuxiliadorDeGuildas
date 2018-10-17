package modelo;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Acoes {

	public static void iniciarPrograma() throws IOException, HeadlessException, AWTException, InterruptedException {
		while (true) {
			executarPassos("Acc");
			wait(3);
		}

	}

	public static void executarPassos(String acc)
			throws AWTException, IOException, HeadlessException, InterruptedException {
		entrar(acc);
		ok(acc);
		fecharJanela(acc);
		ultimaPosicao(acc);
		auxiliar(acc);
		menuGuilda(acc); // Letra G
		membros(acc);
		deixarGuilda(acc);
		deixar(acc);
		fecharJanela(acc);
	}

	public static void entrar(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.ENTRAR, acc);
	}

	public static void ok(String acc) throws HeadlessException, AWTException, IOException, InterruptedException {
		compararImagens(EnumImagens.OK, acc);
		Thread.sleep(1000);
	}

	public static void fecharJanela(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.FECHAR_JANELA, acc);
	}

	public static void ultimaPosicao(String acc)
			throws HeadlessException, AWTException, IOException, InterruptedException {
		Thread.sleep(1000);
		compararImagens(EnumImagens.ULTIMA_POSICAO, acc);
	}

	public static void auxiliar(String acc) throws HeadlessException, AWTException, IOException, InterruptedException {
		boolean temAuxiliar = true;
		boolean primeirasPosicoes = false;
		do {
			do {
				compararImagens(EnumImagens.AUXILIAR, acc);
				Thread.sleep(900);
				compararImagens(EnumImagens.FECHAR_JANELA_RAPIDO, acc);
				temAuxiliar = esperarImagemComLimite(EnumImagens.AUXILIAR, acc);
				Thread.sleep(100);
				compararImagens(EnumImagens.OK_RECOMPENSA, acc);
				Thread.sleep(100);
			} while (temAuxiliar);
			primeirasPosicoes = esperarImagemComLimite(EnumImagens.NUMERO_UM, acc);
			Thread.sleep(300);
			voltarPaginacao(acc);
		} while (!primeirasPosicoes);
		Thread.sleep(1000);
	}

	public static void voltarPaginacao(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.VOLTAR_PAGINACAO, acc);
	}

	public static void menuGuilda(String acc)
			throws HeadlessException, AWTException, IOException, InterruptedException {
		System.out.println("Abrindo menuGuilda");
		InputManager.digitar("g");
		Thread.sleep(1000);
	}

	public static void membros(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.MEMBROS, acc);
	}

	public static void deixarGuilda(String acc)
			throws HeadlessException, AWTException, IOException, InterruptedException {
		boolean temDeixarGuilda = false;
		while (!temDeixarGuilda) {
			temDeixarGuilda = esperarImagemComLimite(EnumImagens.DEIXAR_GUILDA_PAR, acc)
					|| esperarImagemComLimite(EnumImagens.DEIXAR_GUILDA_IMPAR, acc);
			descerMembros(acc);
			Thread.sleep(500);
			compararImagens(EnumImagens.DEIXAR_GUILDA_PAR, acc);
			compararImagens(EnumImagens.DEIXAR_GUILDA_IMPAR, acc);
			Thread.sleep(500);
		}
	}

	public static void descerMembros(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.DESCER_MEMBROS, acc);
		Robot clicker = new Robot();
		for (int i = 0; i < 10; i++) {
			clicker.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
			clicker.delay(20);
			clicker.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
		}
	}

	public static void deixar(String acc) throws HeadlessException, AWTException, IOException, InterruptedException {
		Thread.sleep(500);
		compararImagens(EnumImagens.DEIXAR, acc);
		Thread.sleep(300);
	}

	public static void wait(int segundos) throws AWTException {
		InputManager.clicker = new Robot();
		System.out.print("Wait " + segundos + "s: ");
		for (int i = 0; i < segundos - 1; i++) {
			InputManager.clicker.delay(1000);
			System.out.print(i + 1 + ", ");
		}
		InputManager.clicker.delay(1000);
		System.out.println(segundos);
	}

	public static boolean esperarImagem(BufferedImage bi, String acao, String acc)
			throws HeadlessException, AWTException {
		System.out.println("Procurando imagem " + acao);
		boolean achou = false;
		boolean fail = true;
		while (achou == false) {
			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					if (!achou) {
						boolean invalid = false;
						int k = x, l = y;
						for (int a = 0; a < bi.getWidth(); a++) {
							l = y;
							for (int b = 0; b < bi.getHeight(); b++) {
								if (bi.getRGB(a, b) != image.getRGB(k, l)) {
									invalid = true;
									break;
								} else {
									l++;
								}
							}
							if (invalid) {
								break;
							} else {
								k++;
							}
						}
						if (!invalid) {
							achou = true;
							System.out.println(acao + ": Achou! " + " " + acc);
							fail = false;
						}
					}
				}
			}
		}
		if (fail) {
			System.out.println(acao + ": Nao achou! " + acc);
			achou = false;
		}
		return achou;
	}

	public static boolean esperarImagemComLimite(EnumImagens imagem, String acc)
			throws HeadlessException, AWTException, IOException {
		BufferedImage bi = ImageIO.read(new File(imagem.getPath()));
		boolean achou = false;
		boolean fail = true;
		int count = 0;
		while (achou == false && count < imagem.getParametrosDaImagem().getMaxCount()) {
			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					if (!achou) {
						boolean invalid = false;
						int k = x, l = y;
						for (int a = 0; a < bi.getWidth(); a++) {
							l = y;
							for (int b = 0; b < bi.getHeight(); b++) {
								if (bi.getRGB(a, b) != image.getRGB(k, l)) {
									invalid = true;
									break;
								} else {
									l++;
								}
							}
							if (invalid) {
								break;
							} else {
								k++;
							}
						}
						if (!invalid) {
							achou = true;
							System.out.println(imagem.getAcao() + ": Achou! " + " " + acc);
							fail = false;
						}
					}
				}
			}
			count++;
		}
		if (fail) {
			System.out.println(imagem.getAcao() + ": Nao achou! " + acc);
			achou = false;
		}
		return achou;
	}

	public static boolean verificarImagemDuplicada(int k, int l) {
		return true;
	}

	public static void acharImagem(BufferedImage bi, double widthMult, double heigthMult, int maxCount, String acao,
			String acc) throws HeadlessException, AWTException {
		boolean achou = false;
		boolean fail = true;
		int count = 0;
		while (achou == false && count < maxCount) {
			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					if (!achou) {
						boolean invalid = false;
						int k = x, l = y;
						for (int a = 0; a < bi.getWidth(); a++) {
							l = y;
							for (int b = 0; b < bi.getHeight(); b++) {
								if (bi.getRGB(a, b) != image.getRGB(k, l)) {
									invalid = true;
									break;
								} else {
									l++;
								}
							}
							if (invalid) {
								break;
							} else {
								k++;
							}
						}
						if (!invalid) {
							InputManager.clickEvent(k - (bi.getWidth() * widthMult), l - (bi.getHeight() * heigthMult)); // Clica no centro do objeto
							achou = true;
							System.out.println(acao + ": OK! " + " " + acc);
							fail = false;
						}
					}
				}
			}
			count++;
		}
		if (fail) {
			System.out.println(acao + ": FAIL! " + acc);
		}
	}

	public static void compararImagens(EnumImagens imagem, String acc)
			throws HeadlessException, AWTException, IOException {
		BufferedImage bi = ImageIO.read(new File(imagem.getPath()));
		acharImagem(bi, imagem.getParametrosDaImagem().getWidthMult(), imagem.getParametrosDaImagem().getHeigthMult(),
				imagem.getParametrosDaImagem().getMaxCount(), imagem.getAcao(), acc);
	}

}
