package ua.com.juja.sqlcmd_homework.controller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import ua.com.juja.sqlcmd_homework.model.DataSet;
import ua.com.juja.sqlcmd_homework.model.DatabaseManager;
import ua.com.juja.sqlcmd_homework.view.View;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Sims on 27/10/2015.
 */
public class ClearTestWithMockito {

    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setUp(){
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Clear(manager, view);
    }


    @Test
    public void testClearTable(){
        //given

        command.process("clear|user");
        //when
        verify(manager).clear("user");
        verify(view).write("Table user was successfully cleared");
    }

    @Test
    public void testClearWithParameters() {
        //given

        //when
        boolean canProcess = command.canProcess("clear|user");

        //then
        assertTrue(canProcess);
    }

    @Test
     public void testCantProcessClearWithoutParameters() {
        //given
        Command command = new Find(manager, view);

        //when
        boolean canProcess = command.canProcess("clear");

        //then
        assertFalse(canProcess);
    }

    @Test
    public void testCantProcessClearWithQweParameters() {
        //given


        //when
        boolean canProcess = command.canProcess("qwe|user");

        //then
        assertFalse(canProcess);
    }


}
