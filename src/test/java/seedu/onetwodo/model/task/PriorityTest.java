//@@author A0141138N
package seedu.onetwodo.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PriorityTest {

    @Test
    public void isValidPriority() {
        assertTrue(Priority.isValidPriority('H'));
        assertTrue(Priority.isValidPriority('M'));
        assertTrue(Priority.isValidPriority('L'));
        assertFalse(Priority.isValidPriority(' '));
    }

}