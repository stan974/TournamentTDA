package com.projetTDA;

import java.io.IOException;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
	
	/* ---------------------------------------------------------- */
	/*                        ATTRIBUTS                           */
	/* ---------------------------------------------------------- */
		
		/* -------- */
		/* DATABASE */
		/* -------- */
	
	private static String DB_PATH;
	private static String DB_NAME = "tournament_builder.db";
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	
		/* ------ */
		/* JOUEUR */
		/* ------ */
	//TODO : changer tout en private ?
	public static final String TABLE_JOUEUR = "table_joueur";
	public static final String JOUEUR_COL_ID = "id_joueur";
	public static final String JOUEUR_COL_PSEUDO = "pseudo";
	public static final String JOUEUR_COL_AVATAR = "avatar";
	public static final String CREATE_TABLE_JOUEUR = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_JOUEUR
			+ "(" + JOUEUR_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ JOUEUR_COL_PSEUDO + " VARCHAR(30) NOT NULL, "
			+ JOUEUR_COL_AVATAR + " VARCHAR(30) NOT NULL);";

		/* ------ */
		/* EQUIPE */
		/* ------ */
	
	public static final String TABLE_EQUIPE = "table_equipe";
	public static final String EQUIPE_COL_ID = "id_equipe";
	public static final String EQUIPE_COL_NOM = "nom_equipe";
	public static final String CREATE_TABLE_EQUIPE = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_EQUIPE
			+ "(" + EQUIPE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ EQUIPE_COL_NOM + " VARCHAR(30) NOT NULL);";
	
		/* ------------- */
		/* EQUIPE_JOUEUR */
		/* ------------- */
	
	public static final String TABLE_EQUIPE_JOUEUR = "table_equipe_joueur";
	public static final String EQUIPE_JOUEUR_COL_ID_EQUIPE = "id_equipe";
	public static final String EQUIPE_JOUEUR_COL_ID_JOUEUR = "id_joueur";
	public static final String CREATE_TABLE_EQUIPE_JOUEUR = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_EQUIPE_JOUEUR
			+ "(" + EQUIPE_JOUEUR_COL_ID_EQUIPE + " INTEGER NOT NULL, "
			+ EQUIPE_JOUEUR_COL_ID_JOUEUR + " INTEGER NOT NULL, "
			+ "PRIMARY KEY (" + EQUIPE_JOUEUR_COL_ID_EQUIPE + "," + EQUIPE_JOUEUR_COL_ID_JOUEUR + "));";
	
		/* ------- */
		/* TOURNOI */
		/* ------- */
	
	public static final String TABLE_TOURNOI = "table_tournoi";
	public static final String TOURNOI_COL_ID = "id_tournoi";
	public static final String TOURNOI_COL_NOM = "nom_tournoi";
	public static final String TOURNOI_COL_SPORT = "libelle_sport";
	public static final String TOURNOI_COL_DATE = "date_creation";
	public static final String TOURNOI_COL_CHAMPIONNAT = "is_championnat";
	public static final String CREATE_TABLE_TOURNOI = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_TOURNOI
			+ "(" + TOURNOI_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ TOURNOI_COL_NOM + " VARCHAR(30) NOT NULL, "
			+ TOURNOI_COL_SPORT + " VARCHAR(30) NOT NULL, "
			+ TOURNOI_COL_DATE + " VARCHAR(30) NOT NULL, "
			+ TOURNOI_COL_CHAMPIONNAT + " INTEGER NOT NULL);";
	
		/* ----- */
		/* MATCH */
		/* ----- */
	
	public static final String TABLE_MATCH = "table_match";
	public static final String MATCH_COL_ID_TOURNOI = "id_tournoi";
	public static final String MATCH_COL_ID_EQUIPE_1 = "id_equipe_1";
	public static final String MATCH_COL_ID_EQUIPE_2 = "id_equipe_2";
	public static final String MATCH_COL_SCORE_EQUIPE_1 = "score_equipe_1";
	public static final String MATCH_COL_SCORE_EQUIPE_2 = "score_equipe_2";
	public static final String MATCH_COL_RANG = "rang";
	public static final String CREATE_TABLE_MATCH = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_MATCH
			+ "(" + MATCH_COL_ID_TOURNOI + " INTEGER NOT NULL, "
			+ MATCH_COL_ID_EQUIPE_1 + " INTEGER NOT NULL, "
			+ MATCH_COL_ID_EQUIPE_2 + " INTEGER NOT NULL, "
			+ MATCH_COL_SCORE_EQUIPE_1 + " INTEGER, "
			+ MATCH_COL_SCORE_EQUIPE_2 + " INTEGER, "
			+ MATCH_COL_RANG + " INTEGER, "
			+ "PRIMARY KEY (" + MATCH_COL_ID_TOURNOI + "," + MATCH_COL_ID_EQUIPE_1 + "," + MATCH_COL_ID_EQUIPE_2 + "));";
	
		/* ---------- */
		/* CLASSEMENT */
		/* ---------- */
	
	public static final String TABLE_CLASSEMENT = "table_classement";
	public static final String CLASSEMENT_COL_ID_TOURNOI = "id_tournoi";
	public static final String CLASSEMENT_COL_ID_EQUIPE = "id_equipe";
	public static final String CLASSEMENT_COL_POINTS = "points";
	public static final String CREATE_TABLE_CLASSEMENT = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_CLASSEMENT
			+ "(" + CLASSEMENT_COL_ID_TOURNOI + " INTEGER NOT NULL, "
			+ CLASSEMENT_COL_ID_EQUIPE + " INTEGER NOT NULL, "
			+ CLASSEMENT_COL_POINTS + " INTEGER NOT NULL, "
			+ "PRIMARY KEY (" + CLASSEMENT_COL_ID_TOURNOI + "," + CLASSEMENT_COL_ID_EQUIPE + "));";
	
	/**
	 * 
	 * @param context
	 */
	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
		
		DB_PATH = myContext.getDatabasePath(DB_NAME).getPath();
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void createDataBase() throws IOException {
		
		boolean dbExist = checkDataBase();
		
		if(dbExist) {
			
			System.out.println("La base existe !");
			
		}
		else {
			
			System.out.println("La base n'existe pas ! Création d'une nouvelle base.");
			myDataBase = this.getWritableDatabase();

			if(myDataBase != null)
			{				
				myDataBase.execSQL(CREATE_TABLE_JOUEUR);
				myDataBase.execSQL(CREATE_TABLE_EQUIPE);
				myDataBase.execSQL(CREATE_TABLE_EQUIPE_JOUEUR);
				myDataBase.execSQL(CREATE_TABLE_TOURNOI);
				myDataBase.execSQL(CREATE_TABLE_CLASSEMENT);
				myDataBase.execSQL(CREATE_TABLE_MATCH);
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private boolean checkDataBase() {
		
		SQLiteDatabase checkDB = null;
		
		try
		{
			String myPath = DB_PATH;// + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
			
		} catch(SQLiteException e) {

		}
		
		if(checkDB != null) {
			
			checkDB.close();
			
		}
		
		return checkDB != null ? true : false;
	}
	
	/*private void copyDataBase() throws IOException {
		
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream myOutPut = new FileOutputStream(outFileName);
		
		byte[] buffer = new byte[1024];
		int length;
		
		while((length = myInput.read(buffer)) > 0) {
			
			myOutPut.write(buffer, 0, length);
			
		}
		
		myOutPut.flush();
		myOutPut.close();
		myInput.close();
		
	}*/
	
	/**
	 * 
	 * @throws SQLException
	 */
	public void openDataBase() throws SQLException {
		
		String myPath = DB_PATH;// + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
		
	}
	
	/**
	 * 
	 */
	public synchronized void close() {
		
		if(myDataBase != null) {
			
			myDataBase.close();
			
		}
		
		super.close();
		
	}

	/**
	 * 
	 * @return
	 */
	public SQLiteDatabase getMyDataBase() {
		return myDataBase;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
