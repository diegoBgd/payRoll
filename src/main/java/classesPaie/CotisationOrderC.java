package classesPaie;

import java.io.Serializable;
import java.util.Comparator;

public class CotisationOrderC implements Serializable, Comparator<BulletinCotisationC> {
	private static final long serialVersionUID = -1284898470829870007L;

	public int compare(BulletinCotisationC o1, BulletinCotisationC o2) {
		if (o1.getPriority() < o2.getPriority()) {
			return -1;
		}
		return 1;
	}
}
