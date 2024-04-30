package classesPaie;

import java.io.Serializable;
import java.util.Comparator;

public class PrimeOrderC implements Serializable, Comparator<BulletinPrimeC> {
	private static final long serialVersionUID = 1167031091629520906L;

	public int compare(BulletinPrimeC o1, BulletinPrimeC o2) {
		if (o1.getPriority() < o2.getPriority()) {
			return -1;
		}
		return 1;
	}
}
