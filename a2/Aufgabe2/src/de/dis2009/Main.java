package de.dis2009;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import de.dis2009.data.*;

/**
 * Startet die Hauptanwendung
 * 
 * @author Michael von Riegen
 * @version April 2009
 */
public class Main {

	/**
	 * Startet das Hauptprogramm
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		showMenu();
	}

	/**
	 * Zeigt das Hauptmen��
	 */
	private static void showMenu() {
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			// Zeige Men��
			System.out.println("\n\nHauptmen��:");
			System.out.println("[1] Verwaltung von Mitspielern");
			System.out.println("[2] Verwaltung von Tippspielen");
			System.out.println("[3] Verwaltung von Wettbewerben");
			System.out.println("[4] Einloggen und tippen");
			System.out.println("[5] Programm beenden");
			System.out.print("-- ");
			int todo = Integer.parseInt(stdin.readLine());

			// Rufe Admin- oder Spiel-Screen auf...
			switch (todo) {
			case 1:
				showMitspielerverwaltung();
				break;
			case 2:
				showAdminScreen();
				break;
			case 3:
				showWettbewerb();
				break;			
			case 4:
				showLogin();
			case 5:
				System.out.println("Beende Anwendung...");
				break;
			default:
				System.out.println("Fehler: Geben Sie '1' ein!");
				showMenu();
				break;
			}
		} catch (Exception e) {
			System.out.println("Fehler: Geben Sie '1' ein!");
			showMenu();
		}
	}

	/**
	 * Zeigt das Mitspielermen��
	 */
	private static void showMitspielerverwaltung() {
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println("\n\nMitspielerverwaltungsmen��:");
			System.out.println("[1] Erzeuge neuen Mitspieler");
			System.out.println("[2] L��sche Mitspieler");
			System.out.println("[3] Zur��ck zum Hauptmen��");
			System.out.print("-- ");
			int todo = Integer.parseInt(stdin.readLine());

			switch (todo) {
			case 1:
				showCreateMitspieler();
				break;
			case 2:
				showDeleteMitspieler();
			case 3:
				showMenu();
				break;
			default:
				System.out.println("Fehler: Geben Sie '1' oder '2' ein!");
				showAdminScreen();
				break;
			}
		} catch (Exception e) {
			System.out.println("Fehler: Geben Sie '1' oder '2' ein!");
			showAdminScreen();
		}
	}

	public static void showCreateMitspieler(){
		
		try{
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));

		System.out.println("Bitte gib den Vornamen des neuen Mispielers ein:");
		String vorname = stdin.readLine();
		System.out.println("Bitte gib den Nachnamen des neuen Mitspielers ein:");
		String name = stdin.readLine();
		System.out.println("Bitte gib das Passwort des neuen Mitspielers ein:");
		String passwort = stdin.readLine();
		Mitspieler mit=new Mitspieler();
		mit.set_vorname(vorname);
		mit.set_name(name);
		mit.set_passwort(passwort);
		mit.save();
		System.out.println("Mitspieler wurde erstellt:");
		System.out.println("ID:"+mit.getId());
		System.out.println("Vorname:"+mit.get_vorname());
		System.out.println("Nachname"+mit.get_name());
		System.out.println("Passwort:"+mit.get_passwort());
		System.out.println("ACHTUNG: Bitte merke dir deine ID. Sie ist wichtig um dich einzuloggen und um an Tippspielen teilzunehmen!");
		showMitspielerverwaltung();
		
		}
		catch(Exception e){
			System.out.println("Fehler");
			showMitspielerverwaltung();
		}
		
	}
	
public static void showDeleteMitspieler(){
		
		try{
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));

		System.out.println("Bitte gib die ID des zu l��schenden Mitspielers ein");
		int id = Integer.parseInt(stdin.readLine());
		Mitspieler mit=new Mitspieler();
		mit.delete(id);
		
		showMitspielerverwaltung();
		
		}
		catch(Exception e){
			System.out.println("Fehler");
			showMitspielerverwaltung();
		}
		
	}

	/**
	 * Zeigt das Verwaltungsmen��
	 */
	private static void showAdminScreen() {
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println("\n\nVerwaltungsmen��:");
			System.out.println("[1] Erzeuge neues Tippspiel");
			System.out.println("[2] Lade Tippspiel");
			System.out.println("[3] L��sche Tippspiel");
			System.out.println("[4] Zur��ck zum Hauptmen��");
			System.out.print("-- ");
			int todo = Integer.parseInt(stdin.readLine());

			switch (todo) {
			case 1:
				showCreateGameScreen();
				break;
			case 2:
				showLoadTippspiel();
			case 3:
				showDeleteTippspiel();
			case 4:
				showMenu();
				break;
			default:
				System.out.println("Fehler: Geben Sie '1' oder '2' ein!");
				showAdminScreen();
				break;
			}
		} catch (Exception e) {
			System.out.println("Fehler: Geben Sie '1' oder '2' ein!");
			showAdminScreen();
		}
	}

	/**
	 * Screen zum Erzeugen eines neuen Tippspiels
	 */
	private static void showCreateGameScreen() {
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			// Frage nach Namen
			System.out.println("\n\nErzeuge neues Tippspiel:");
			System.out.print("Name des Spiels? ");
			String name = stdin.readLine();
			System.out.print("Verwalter des Spiels? (id)");
			int verwalter = Integer.parseInt(stdin.readLine());
			System.out.print("Welcher Wettbewerb soll getippt werden?");
			String wettbewerb = stdin.readLine();
			
			System.out.println("Spiel wird angelegt. Bitte warten...");

			// Erzeuge neues Tippspiel und speichere es
			Tippspiel ts = new Tippspiel();
			ts.set_name(name);
			ts.set_verwalter(verwalter);
			ts.set_wettbewerb(wettbewerb);
			ts.save();

			System.out.println("Tippspiel '" + ts.get_name()
					+ "' wurde mit Id " + ts.get_id() + " gespeichert");

			// ... danach zeige wieder den Adminscreen
			showAdminScreen();

		} catch (Exception e) {
			showAdminScreen();
		}
	}
	
	
	public static void showLoadTippspiel(){
			
			try{
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println("Bitte gib die ID des zu ladenden Tippspiel ein");
			int id = Integer.parseInt(stdin.readLine());
			Tippspiel ts = new Tippspiel();
			ts.load(id);
			showEditTippspiel(id);
			
			}
			catch(Exception e){
				System.out.println("Fehler");
				showAdminScreen();
			}
			
		}
	
	
	public static void showDeleteTippspiel(){
			
			try{
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println("Bitte gib die ID des zu l��schenden Tippspiels ein");
			int id = Integer.parseInt(stdin.readLine());
			Tippspiel ts = new Tippspiel();
			ts.delete(id);
			
			showAdminScreen();
			
			}
			catch(Exception e){
				System.out.println("Fehler");
				showAdminScreen();
			}
			
		}
	
	private static void showEditTippspiel(int tippspielid) {
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println("\n\nEditieren eines Tippspiels:");
			System.out.println("[1] F��ge Spieler hinzu");
			System.out.println("[2] L��sche Spieler");
			System.out.println("[3] Zur��ck zum Hauptmen��");
			System.out.print("-- ");
			int todo = Integer.parseInt(stdin.readLine());

			switch (todo) {
			case 1:
				showAddTipper(tippspielid);
			case 2:
				showDeleteTipper(tippspielid);
			case 3:
				showMenu();
				break;
			default:
				System.out.println("Fehler: Geben Sie '1' oder '2' ein!");
				showEditTippspiel(tippspielid);
				break;
			}
		} catch (Exception e) {
			System.out.println("Fehler: Geben Sie '1' oder '2' ein!");
			showAdminScreen();
		}
	}
	
	private static void showAddTipper(int tsid) {
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println("\n\nEinf��gen eines Mittspielers:");
			System.out.println("Bitte gib die ID des Spielers ein, der hinzugef��gt werden soll.");
			int mitspielerid = Integer.parseInt(stdin.readLine());
			Spielt sp = Spielt.create_Spielt_Instanz(mitspielerid, tsid);
			sp.save();
			showAdminScreen();
		}catch (Exception e) {
			System.out.println("Fehler: Geben Sie '1' oder '2' ein!");
			showAdminScreen();
		}
	}
	private static void showDeleteTipper(int tsid) {
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println("\n\nEinf��gen eines Mittspielers:");
			System.out.println("Bitte gib die ID des Spielers ein, der gel��scht werden soll.");
			int mitspielerid = Integer.parseInt(stdin.readLine());
			Spielt sp=Spielt.create_Spielt_Instanz(mitspielerid, tsid);
			sp.delete(mitspielerid, tsid);
			showAdminScreen();
		}catch (Exception e) {
			System.out.println("Fehler: Geben Sie '1' oder '2' ein!");
			showAdminScreen();
		}
	}
	
	private static void showWettbewerb() {
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			// Zeige Men��
			System.out.println("\n\nHauptmen��:");
			System.out.println("[1] Wettbewerb hinzuf��gen");
			System.out.println("[2] Wettbewerb bearbeiten");
			System.out.println("[3] Zur��ck zum Hauptmen��");
			System.out.print("-- ");
			int todo = Integer.parseInt(stdin.readLine());

			// Rufe Admin- oder Spiel-Screen auf...
			switch (todo) {
			case 1:
				showAddWettbewerb();
				break;
			case 2:
				showEditWettbewerb();
				break;
			case 3:
				System.out.println("Beende Anwendung...");
				break;
			default:
				System.out.println("Fehler: Geben Sie '1' ein!");
				showMenu();
				break;
			}
		} catch (Exception e) {
			System.out.println("Fehler: Geben Sie '1' ein!");
			showMenu();
		}
	}
	
	
	private static void showAddWettbewerb() {
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println("\n\nEinf��gen eines Wettbewerbs:");
			System.out.println("Bitte gib den Namen des Wettbewerbs ein:");
			String name = stdin.readLine();			
			Wettbewerb wett = new Wettbewerb(name);
			wett.save();
		}catch (Exception e) {
			System.out.println("Fehler: Geben Sie '1' oder '2' ein!");
			showAdminScreen();
		}
	}
	
	private static void showEditWettbewerb() {
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println("\n\nAuswahl eines Wettbewerbs:");
			System.out.println("Bitte gib den Namen des Wettbewerbs ein:");
			String name = stdin.readLine();			
			
			// Zeige Men��
			System.out.println("\n\nWettbewerb bearbeiten:");
			System.out.println("[1] Begegnung hinzuf��gen");
			System.out.println("[2] Zur��ck");
			System.out.print("-- ");
			int todo = Integer.parseInt(stdin.readLine());

			// Rufe Admin- oder Spiel-Screen auf...
			switch (todo) {
			case 1:
				showAddBegegnung(name);
			case 2:
				System.out.println("Beende Anwendung...");
				break;
			default:
				System.out.println("Fehler: Geben Sie '1' ein!");
				showMenu();
				break;
			}
		} catch (Exception e) {
			System.out.println("Fehler: Geben Sie '1' ein!");
			showWettbewerb();
		}
	}
	
	private static void showAddBegegnung(String name) {
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println("\n\nEinf��gen einer Begegnung:");
			System.out.println("Bitte w��hle die Sportart aus: [1] Fussball, [2] Volleyball");
			int spart = Integer.parseInt(stdin.readLine());
			

			
			System.out.println("Bitte gib das Datum f��r die Begegnung an: [TT:MM:YY]");
			String datum = stdin.readLine();
			System.out.println("Bitte gib die Uhrzeit f��r die Begegnung an: [HH:MM]");
			String uhrzeit = stdin.readLine();
			System.out.println("Bitte gib Den Namen des Heimteams an:");
			String teamA = stdin.readLine();
			System.out.println("Bitte gib Den Namen des Ausw��rtsteams an:");
			String teamB = stdin.readLine();
			
			Begegnung beg = new Begegnung();
			beg.set_datum(datum);
			beg.set_uhrzeit(uhrzeit);
			beg.set_teamA(teamA);
			beg.set_teamB(teamB);
			beg.save(name);
			int begid = beg.get_begId();
			
			if( spart == 1 ) {
				Fussballspiel f = new Fussballspiel();
				f.set_begId(begid);
				f.save();
			} else if ( spart == 2) {
				Volleyballspiel v = new Volleyballspiel();
				v.set_begId(begid);
				v.save();
			}
			
			System.out.println("Die Begegnungs ID ist:"+begid);
			showWettbewerb();

		}catch (Exception e) {
			System.out.println("Fehler: Geben Sie '1' oder '2' ein!");
			showAdminScreen();
		}
	}
	
	private static void showLogin() {
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println("\n\nLogin:");
			System.out.println("Bitte gib deine Benutzer ID ein");
			int userid = Integer.parseInt(stdin.readLine());			
			System.out.println("Bitte gib dein Passwort ein");
			String pass = stdin.readLine();
			System.out.println("Die Daten werden verglichen. Bitte warten ...");
			
			Mitspieler mit = new Mitspieler();
			mit.setId(userid);
			mit.set_passwort(pass);
			mit.login();
			
			Spielt sp = new Spielt();
			sp.load(userid);
			
			Tippspiel tipp = new Tippspiel();
			int x = sp.get_tippspielid();
			tipp.load(x);
			
			
			showUserMenu(userid, x); //Spielerid und Tippspielid mitgeben
			
			
		}catch (Exception e) {
			System.out.println("Login Fehlgeschlagen. Bitte erneut versuchen.");
			showMenu();
		}
	}
	
	
	private static void showUserMenu(int id, int spid) {
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			// Zeige Men��
			System.out.println("\n\nBenutzer Men��:");
			System.out.println("[1] Tipps abgeben");
			System.out.println("[2] History angucken");
			System.out.println("[3] Punktestand ansehen");
			System.out.println("[4] Zur��ck zum Hauptmen��");
			System.out.print("-- ");
			int todo = Integer.parseInt(stdin.readLine());

			// Rufe Admin- oder Spiel-Screen auf...
			switch (todo) {
			case 1:
	//TODO			showTippsAbgeben(id); //Tippspielid mitgeben
			case 2:
	//TODO			showHistory();
			case 3:
	//TODO			showPunkte();
			case 4:
				showMenu();
			default:
				System.out.println("Fehler: Geben Sie '1' ein!");
				showMenu();
				break;
			}
		} catch (Exception e) {
			System.out.println("Fehler: Geben Sie '1' ein!");
			showMenu();
		}
	}
	
	private static void showTippsAbgeben(int id) {
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			
			System.out.println("\n\nTipabgabe f��r das Spiel:"); //TODO Hier muss noch die Begegnung kommen. Wie an die BegID kommen?
			System.out.println("Wieviel Tore/S��tze macht das Heimteam?");
			int heim = Integer.parseInt(stdin.readLine());			
			System.out.println("Wieviel Tore/S��tze macht das Heimteam?");
			int ausw = Integer.parseInt(stdin.readLine());
			System.out.println("Die Daten werden gespeichert. Bitte warten ...");
			
			

			
			
		}catch (Exception e) {
			System.out.println("Login Fehlgeschlagen. Bitte erneut versuchen.");
			showMenu();
		}
	}
	
	
}