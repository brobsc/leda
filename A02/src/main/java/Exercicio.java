import java.util.Date;
import java.util.stream.IntStream;

public class Exercicio {
  public static void main(String[] args) {
    System.out.println(somatorio(1, 5));
    System.out.println(funcsomatorio(1, 5));
  }

  public static int somatorio (int n, int m) {
    int soma = 0;
    int mult = 1;

    for (int i = 1; i <= n; i++ ) {
      soma += (int) Math.pow(i, 2);
    }

    for (int i = 1; i <= m/2 ; i++ ) {
      mult = mult * i;
    }

    return soma + mult;
  }

  public static int funcsomatorio (int n, int m) {
    int soma = 1 + IntStream.range(1, n + 1)
        .map(i -> (int) Math.pow(i, 2))
        .reduce(Integer::sum)
        .orElse(0);

    int mult = IntStream.range(1, ((n + 1) / 2))
        .reduce(1, ((i, i1) -> i * i1));

    return soma + mult;
  }
}
