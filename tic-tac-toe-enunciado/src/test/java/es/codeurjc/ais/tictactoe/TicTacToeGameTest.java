/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.codeurjc.ais.tictactoe;


import es.codeurjc.ais.tictactoe.Player;
import es.codeurjc.ais.tictactoe.TicTacToeGame;
import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;
import net.bytebuddy.agent.builder.ResettableClassFileTransformer;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;




import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;
import static org.mockito.Mockito.reset;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author adgao
 */
public class TicTacToeGameTest {
	

    private TicTacToeGame game;
    private Player p1;
    private Player p2;
    private Connection c1;
    private Connection c2;
    
    @Before
    public void setUp() {
       p1=new Player(0, "pepe", "pepe");
       p2=new Player(1, "juan", "juan");
       game=new TicTacToeGame();
       c1=mock(Connection.class);
       c2=mock(Connection.class);
       game.addConnection(c1);
       game.addConnection(c2);
    }
    
     @Test
     public void ganaJugador1() {

    	 game.addPlayer(p1);       	 
    	 game.addPlayer(p2);        
    	 verify(c1, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1,p2)));
    	 verify(c2, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1,p2)));
    	 game.mark(0);
    	 verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
    	 game.mark(1);
    	 verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
    	 game.mark(3);
    	 verify(c2,times(2)).sendEvent(eq(EventType.SET_TURN), eq(p1));
    	 game.mark(4);
    	 verify(c1,times(2)).sendEvent(eq(EventType.SET_TURN), eq(p2));
    	 game.mark(6);
    	 ArgumentCaptor<WinnerValue> argument =
			 ArgumentCaptor.forClass(WinnerValue.class);
    	 verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
    	 WinnerValue event =argument.getValue();
    	 assertThat(event.player,equalTo(p1));
    	 int[] casillas= {0,3,6};  	 	
    	 assertThat(event.pos,equalTo(casillas));
     }
     @Test
     public void ganaJugador2() {
    	 game.addPlayer(p1);       	 
    	 game.addPlayer(p2);        
    	 verify(c1, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1,p2)));
    	 verify(c2, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1,p2)));
    	 game.mark(0);
    	 verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
    	 game.mark(1);
    	 verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
    	 game.mark(3);
    	 verify(c2,times(2)).sendEvent(eq(EventType.SET_TURN), eq(p1));
    	 game.mark(4);
    	 verify(c1,times(2)).sendEvent(eq(EventType.SET_TURN), eq(p2));
    	 game.mark(2);
    	 verify(c1,times(3)).sendEvent(eq(EventType.SET_TURN), eq(p1));
    	 game.mark(7);
    	 ArgumentCaptor<WinnerValue> argument =
			 ArgumentCaptor.forClass(WinnerValue.class);
    	 verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
    	 WinnerValue event =argument.getValue();
    	 assertThat(event.player,equalTo(p2));
    	 int[] casillas= {1,4,7};  	 	
    	 assertThat(event.pos,equalTo(casillas));
     }
     @Test
     public void empate() {
    	 game.addPlayer(p1);       	 
    	 game.addPlayer(p2);        
    	 verify(c1, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1,p2)));
    	 verify(c2, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1,p2)));
    	 game.mark(0);
    	 verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
    	 game.mark(1);
    	 verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
    	 game.mark(3);
    	 verify(c2,times(2)).sendEvent(eq(EventType.SET_TURN), eq(p1));
    	 game.mark(4);
    	 verify(c1,times(2)).sendEvent(eq(EventType.SET_TURN), eq(p2));
    	 game.mark(2);
    	 verify(c1,times(3)).sendEvent(eq(EventType.SET_TURN), eq(p1));
    	 game.mark(6);
    	 verify(c2,times(3)).sendEvent(eq(EventType.SET_TURN), eq(p2));
    	 game.mark(7);
    	 verify(c1,times(4)).sendEvent(eq(EventType.SET_TURN), eq(p1));
    	 game.mark(8);
    	 verify(c2,times(4)).sendEvent(eq(EventType.SET_TURN), eq(p2));
    	 game.mark(5);
    	 ArgumentCaptor<WinnerValue> argument =
			 ArgumentCaptor.forClass(WinnerValue.class);
    	 verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
    	 WinnerValue event =argument.getValue();
    	 assertThat(event,equalTo(null)); 	 	    	
     }
}
