package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.assertThat;


import java.util.Arrays;
import java.util.Collection;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Before;




import es.codeurjc.ais.tictactoe.Board;
import es.codeurjc.ais.tictactoe.Player;
import es.codeurjc.ais.tictactoe.TicTacToeGame;

import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

public class boardTest {
	private Player jugador1;
	private Player jugador2;
	private Board tablero;
	
	@Before
	public void setUp() {
		jugador1=new Player(1, "pepe", "pepe");
		jugador2=new Player(2, "manolo", "manolo");
		tablero=new Board();
		tablero.enableAll();
	}
	
	@Test
	public void winTest() {
		//Jugador1 gana
		tablero.getCell(0).value=jugador1.getLabel();
		tablero.getCell(0).active=false;
		tablero.getCell(3).value=jugador2.getLabel();
		tablero.getCell(3).active=false;
		tablero.getCell(1).value=jugador1.getLabel();
		tablero.getCell(1).active=false;
		tablero.getCell(4).value=jugador2.getLabel();
		tablero.getCell(4).active=false;
		tablero.getCell(2).value=jugador1.getLabel();
		tablero.getCell(2).active=false;

		int[] introducidos = {0,1,2};
		assertThat(introducidos,equalTo(tablero.getCellsIfWinner(jugador1.getLabel())));
		assertThat(null,equalTo(tablero.getCellsIfWinner(jugador2.getLabel())));
		
		assertThat(false,equalTo(tablero.checkDraw()));
	}
	
	@Test
	public void loseTest() {
		//Jugador1 pierde
		tablero.getCell(3).value=jugador1.getLabel();
		tablero.getCell(3).active=false;
		tablero.getCell(0).value=jugador2.getLabel();
		tablero.getCell(0).active=false;
		tablero.getCell(6).value=jugador1.getLabel();
		tablero.getCell(6).active=false;
		tablero.getCell(4).value=jugador2.getLabel();
		tablero.getCell(4).active=false;
		tablero.getCell(1).value=jugador1.getLabel();
		tablero.getCell(1).active=false;
		tablero.getCell(7).value=jugador2.getLabel();
		tablero.getCell(7).active=false;
		tablero.getCell(2).value=jugador1.getLabel();
		tablero.getCell(2).active=false;
		tablero.getCell(8).value=jugador2.getLabel();
		tablero.getCell(8).active=false;
                
		int[] introducidos = {0,4,8};
		assertThat(null,equalTo(tablero.getCellsIfWinner(jugador1.getLabel())));
		assertThat(introducidos,equalTo(tablero.getCellsIfWinner(jugador2.getLabel())));
		
		assertThat(false,equalTo(tablero.checkDraw()));
	}
	
	@Test
	public void drawTest() {
		//Empate
		tablero.getCell(0).value=jugador1.getLabel();
		tablero.getCell(0).active=false;
		tablero.getCell(2).value=jugador2.getLabel();
		tablero.getCell(2).active=false;
		tablero.getCell(1).value=jugador1.getLabel();
		tablero.getCell(1).active=false;
		tablero.getCell(3).value=jugador2.getLabel();
		tablero.getCell(3).active=false;
		tablero.getCell(5).value=jugador1.getLabel();
		tablero.getCell(5).active=false;
		tablero.getCell(4).value=jugador2.getLabel();
		tablero.getCell(4).active=false;
		tablero.getCell(6).value=jugador1.getLabel();
		tablero.getCell(6).active=false;
		tablero.getCell(7).value=jugador2.getLabel();
		tablero.getCell(7).active=false;
		tablero.getCell(8).value=jugador1.getLabel();
		tablero.getCell(8).active=false;
		
		assertThat(null,equalTo(tablero.getCellsIfWinner(jugador1.getLabel())));
		assertThat(null,equalTo(tablero.getCellsIfWinner(jugador2.getLabel())));
		
		assertThat(true,equalTo(tablero.checkDraw()));
	}

}
