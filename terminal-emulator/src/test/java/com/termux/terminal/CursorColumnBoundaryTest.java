package com.termux.terminal;

/**
 * Tests that cursor column never goes negative when combining/zero-width characters are emitted,
 * covering the race-condition guard introduced in TerminalEmulator.emitCodePoint().
 */
public class CursorColumnBoundaryTest extends TerminalTestCase {

    public void testCombiningCharAtColumnZeroDoesNotCrash() throws Exception {
        // U+0300 COMBINING GRAVE ACCENT has display width 0 and is a combining character.
        // Emitting it when the cursor is at column 0 should not throw or produce a negative column.
        withTerminalSized(10, 5);
        enterString("A");
        // Move cursor back to column 0
        enterString("\r");
        assertCursorAt(0, 0);
        // Send a combining character — cursor should stay >= 0
        mTerminal.append(new byte[]{(byte) 0xCC, (byte) 0x80}, 2); // UTF-8 for U+0300
        assertTrue("Cursor column must be >= 0", mTerminal.getCursorCol() >= 0);
        assertTrue("Cursor row must be >= 0", mTerminal.getCursorRow() >= 0);
    }

    public void testCombiningCharAfterNormalChar() throws Exception {
        withTerminalSized(10, 5);
        enterString("A");
        int colBefore = mTerminal.getCursorCol();
        assertTrue("Cursor must be after 'A'", colBefore > 0);
        // Combining grave on top of 'A'
        mTerminal.append(new byte[]{(byte) 0xCC, (byte) 0x80}, 2); // U+0300
        // Column should stay the same (combining chars don't advance cursor)
        assertEquals(colBefore, mTerminal.getCursorCol());
        assertTrue("Cursor column must be >= 0", mTerminal.getCursorCol() >= 0);
    }

    public void testCursorDoesNotExceedColumns() throws Exception {
        withTerminalSized(5, 3);
        // Fill the line to the end
        enterString("ABCDE");
        assertTrue("Cursor column within bounds",
            mTerminal.getCursorCol() < mTerminal.mColumns);
    }
}
