package com.tiku.ssm.utils;



import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.Statement; 
import java.util.ArrayList; 
import java.util.List; 

import com.jacob.activeX.ActiveXComponent; 
import com.jacob.com.Dispatch; 
import com.jacob.com.Variant; 

public class MSWordManager { 
     
        private Dispatch doc; 
        private ActiveXComponent word; 
        private Dispatch documents; 
        private Dispatch selection; 
        private boolean saveOnExit = true; 

        public MSWordManager(boolean visible) { 
                if (word == null) { 
                        word = new ActiveXComponent("Word.Application"); 
                        word.setProperty("Visible", new Variant(visible)); 
                } 
                if (documents == null) 
                        documents = word.getProperty("Documents").toDispatch(); 
        } 

 
        public void setSaveOnExit(boolean saveOnExit) { 
                this.saveOnExit = saveOnExit; 
        } 

     
        public void createNewDocument() { 
                doc = Dispatch.call(documents, "Add").toDispatch(); 
                selection = Dispatch.get(word, "Selection").toDispatch(); 
        } 

     
        public void openDocument(String docPath) { 
                closeDocument(); 
                doc = Dispatch.call(documents, "Open", docPath).toDispatch(); 
                selection = Dispatch.get(word, "Selection").toDispatch(); 
        } 

       
        public void moveUp(int pos) { 
                if (selection == null) 
                        selection = Dispatch.get(word, "Selection").toDispatch(); 
                for (int i = 0; i < pos; i++) 
                        Dispatch.call(selection, "MoveUp"); 

        } 

       
        public void moveDown(int pos) { 
                if (selection == null) 
                        selection = Dispatch.get(word, "Selection").toDispatch(); 
                for (int i = 0; i < pos; i++) 
                        Dispatch.call(selection, "MoveDown"); 
        } 

       
        public void moveLeft(int pos) { 
                if (selection == null) 
                        selection = Dispatch.get(word, "Selection").toDispatch(); 
                for (int i = 0; i < pos; i++) { 
                        Dispatch.call(selection, "MoveLeft"); 
                } 
        } 

       
        public void moveRight(int pos) { 
                if (selection == null) 
                        selection = Dispatch.get(word, "Selection").toDispatch(); 
                for (int i = 0; i< pos; i++) 
                        Dispatch.call(selection, "MoveRight"); 
        } 

       
        public void moveStart() { 
                if (selection == null) 
                        selection = Dispatch.get(word, "Selection").toDispatch(); 
                Dispatch.call(selection, "HomeKey", new Variant(6)); 
        } 
         
        public void moveEnd() { 
                if (selection == null) 
                        selection = Dispatch.get(word, "Selection").toDispatch(); 
                Dispatch.call(selection, "EndKey", new Variant(6)); 
        } 

        /** *//** 
         * ��ѡ�����ݻ����㿪ʼ�����ı� 
         *     
         * @param toFindText Ҫ���ҵ��ı� 
         * @return boolean true-���ҵ���ѡ�и��ı���false-δ���ҵ��ı� 
         */ 
        public boolean find(String toFindText) { 
                if (toFindText == null || toFindText.equals("")) 
                        return false; 
               
                Dispatch find = word.call(selection, "Find").toDispatch(); 
               
                Dispatch.put(find, "Text", toFindText); 
               
                Dispatch.put(find, "Forward", "True"); 
                // ���ø�ʽ 
                Dispatch.put(find, "Format", "True"); 
                // ��Сдƥ�� 
                Dispatch.put(find, "MatchCase", "True"); 
                // ȫ��ƥ�� 
                Dispatch.put(find, "MatchWholeWord", "True"); 
                // ���Ҳ�ѡ�� 
                return Dispatch.call(find, "Execute").getBoolean(); 
        } 

        /** *//** 
         * ��ѡ��ѡ�������趨Ϊ�滻�ı� 
         *     
         * @param toFindText �����ַ��� 
         * @param newText Ҫ�滻������ 
         * @return 
         */ 
        public boolean replaceText(String toFindText, String newText) { 
                if (!find(toFindText)) 
                        return false; 
                Dispatch.put(selection, "Text", newText); 
                return true; 
        } 

        /** *//** 
         * ȫ���滻�ı� 
         *     
         * @param toFindText �����ַ��� 
         * @param newText Ҫ�滻������ 
         */ 
        public void replaceAllText(String toFindText, String newText) { 
                while (find(toFindText)) { 
                        Dispatch.put(selection, "Text", newText); 
                        Dispatch.call(selection, "MoveRight"); 
                } 
        } 

        /** *//** 
         * �ڵ�ǰ���������ַ��� 
         *     
         * @param newText Ҫ��������ַ��� 
         */ 
        public void insertText(String newText) { 
                Dispatch.put(selection, "Text", newText); 
        } 

        /** *//** 
         *     
         * @param toFindText Ҫ���ҵ��ַ��� 
         * @param imagePath ͼƬ·�� 
         * @return 
         */ 
        public boolean replaceImage(String toFindText, String imagePath) { 
                if (!find(toFindText)) 
                        return false; 
                Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), 
                                "AddPicture", imagePath); 
                return true; 
        } 

        /** *//** 
         * ȫ���滻ͼƬ 
         *     
         * @param toFindText �����ַ��� 
         * @param imagePath ͼƬ·�� 
         */ 
        public void replaceAllImage(String toFindText, String imagePath) { 
                while (find(toFindText)) { 
                        Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), 
                                        "AddPicture", imagePath); 
                        Dispatch.call(selection, "MoveRight"); 
                } 
        } 

        /** *//** 
         * �ڵ�ǰ��������ͼƬ 
         *     
         * @param imagePath ͼƬ·�� 
         */ 
        public void insertImage(String imagePath) { 
                Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), 
                                "AddPicture", imagePath); 
        } 

        /** *//** 
         * �ϲ���Ԫ�� 
         *     
         * @param tableIndex 
         * @param fstCellRowIdx 
         * @param fstCellColIdx 
         * @param secCellRowIdx 
         * @param secCellColIdx 
         */ 
        public void mergeCell(int tableIndex, int fstCellRowIdx, int fstCellColIdx, 
                        int secCellRowIdx, int secCellColIdx) { 
                // ���б�� 
                Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); 
                // Ҫ���ı�� 
                Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)) 
                                .toDispatch(); 
                Dispatch fstCell = Dispatch.call(table, "Cell", 
                                new Variant(fstCellRowIdx), new Variant(fstCellColIdx)) 
                                .toDispatch(); 
                Dispatch secCell = Dispatch.call(table, "Cell", 
                                new Variant(secCellRowIdx), new Variant(secCellColIdx)) 
                                .toDispatch(); 
                Dispatch.call(fstCell, "Merge", secCell); 
        } 

        /** *//** 
         * ��ָ���ĵ�Ԫ������д���� 
         *     
         * @param tableIndex 
         * @param cellRowIdx 
         * @param cellColIdx 
         * @param txt 
         */ 
        public void putTxtToCell(int tableIndex, int cellRowIdx, int cellColIdx, 
                        String txt) { 
                // ���б�� 
                Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); 
                // Ҫ���ı�� 
                Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)) 
                                .toDispatch(); 
                Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx), 
                                new Variant(cellColIdx)).toDispatch(); 
                Dispatch.call(cell, "Select"); 
                Dispatch.put(selection, "Text", txt); 
        } 

        /** *//** 
         * �ڵ�ǰ�ĵ��������� 
         *     
         * @param pos 
         */ 
        public void copy(String toCopyText) { 
                moveStart(); 
                if (this.find(toCopyText)) { 
                        Dispatch textRange = Dispatch.get(selection, "Range").toDispatch(); 
                        Dispatch.call(textRange, "Copy"); 
                } 
        } 

        /** *//** 
         * �ڵ�ǰ�ĵ�ճ������������ 
         *     
         * @param pos 
         */ 
        public void paste(String pos) { 
                moveStart(); 
                if (this.find(pos)) { 
                        Dispatch textRange = Dispatch.get(selection, "Range").toDispatch(); 
                        Dispatch.call(textRange, "Paste"); 
                } 
        } 

        /** *//** 
         * �ڵ�ǰ�ĵ�ָ����λ�ÿ������ 
         *     
         * @param pos ��ǰ�ĵ�ָ����λ�� 
         * @param tableIndex �������ı����word�ĵ���������λ�� 
         */ 
        public void copyTable(String pos,int tableIndex) { 
                Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); 
                Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)) 
                                .toDispatch(); 
                Dispatch range = Dispatch.get(table, "Range").toDispatch(); 
                Dispatch.call(range, "Copy"); 
                if (this.find(pos)) { 
                        Dispatch textRange = Dispatch.get(selection, "Range").toDispatch(); 
                        Dispatch.call(textRange, "Paste"); 
                } 
        } 

        /** *//** 
         * �ڵ�ǰ�ĵ�ĩβ����������һ���ĵ��еĶ��� 
         *     
         * @param anotherDocPath ��һ���ĵ��Ĵ���·�� 
         * @param tableIndex �������Ķ�������һ���ĵ��е����(��1��ʼ) 
         */ 
        public void copyParagraphFromAnotherDoc(String anotherDocPath, 
                        int paragraphIndex) { 
                Dispatch wordContent = Dispatch.get(doc, "Content").toDispatch(); // ȡ�õ�ǰ�ĵ������� 
                Dispatch.call(wordContent, "InsertAfter", "$selection$");// �����������λ����� 
                copyParagraphFromAnotherDoc(anotherDocPath, paragraphIndex, 
                                "$selection$"); 
        } 

        /** *//** 
         * �ڵ�ǰ�ĵ�ָ����λ�ÿ���������һ���ĵ��еĶ��� 
         *     
         * @param anotherDocPath ��һ���ĵ��Ĵ���·�� 
         * @param tableIndex �������Ķ�������һ���ĵ��е����(��1��ʼ) 
         * @param pos ��ǰ�ĵ�ָ����λ�� 
         */ 
        public void copyParagraphFromAnotherDoc(String anotherDocPath, 
                        int paragraphIndex, String pos) { 
                Dispatch doc2 = null; 
                try { 
                        doc2 = Dispatch.call(documents, "Open", anotherDocPath) 
                                        .toDispatch(); 
                        Dispatch paragraphs = Dispatch.get(doc2, "Paragraphs").toDispatch(); 

                        Dispatch paragraph = Dispatch.call(paragraphs, "Item", 
                                        new Variant(paragraphIndex)).toDispatch(); 
                        Dispatch range = Dispatch.get(paragraph, "Range").toDispatch(); 
                        Dispatch.call(range, "Copy"); 
                        if (this.find(pos)) { 
                                Dispatch textRange = Dispatch.get(selection, "Range") 
                                                .toDispatch(); 
                                Dispatch.call(textRange, "Paste"); 
                        } 
                } catch (Exception e) { 
                        e.printStackTrace(); 
                } finally { 
                        if (doc2 != null) { 
                                Dispatch.call(doc2, "Close", new Variant(saveOnExit)); 
                                doc2 = null; 
                        } 
                } 
        } 

        /** *//** 
         * �ڵ�ǰ�ĵ�ָ����λ�ÿ���������һ���ĵ��еı�� 
         *     
         * @param anotherDocPath ��һ���ĵ��Ĵ���·�� 
         * @param tableIndex �������ı������һ���ĵ��е����(��1��ʼ) 
         * @param pos ��ǰ�ĵ�ָ����λ�� 
         */ 
        public void copyTableFromAnotherDoc(String anotherDocPath, int tableIndex, 
                        String pos) { 
                Dispatch doc2 = null; 
                try { 
                        doc2 = Dispatch.call(documents, "Open", anotherDocPath) 
                                        .toDispatch(); 
                        Dispatch tables = Dispatch.get(doc2, "Tables").toDispatch(); 
                        Dispatch table = Dispatch.call(tables, "Item", 
                                        new Variant(tableIndex)).toDispatch(); 
                        Dispatch range = Dispatch.get(table, "Range").toDispatch(); 
                        Dispatch.call(range, "Copy"); 
                        if (this.find(pos)) { 
                                Dispatch textRange = Dispatch.get(selection, "Range") 
                                                .toDispatch(); 
                                Dispatch.call(textRange, "Paste"); 
                        } 
                } catch (Exception e) { 
                        e.printStackTrace(); 
                } finally { 
                        if (doc2 != null) { 
                                Dispatch.call(doc2, "Close", new Variant(saveOnExit)); 
                                doc2 = null; 
                        } 
                } 
        } 

        /** *//** 
         * �ڵ�ǰ�ĵ�ָ����λ�ÿ���������һ���ĵ��е�ͼƬ 
         *     
         * @param anotherDocPath ��һ���ĵ��Ĵ���·�� 
         * @param shapeIndex ��������ͼƬ����һ���ĵ��е�λ�� 
         * @param pos ��ǰ�ĵ�ָ����λ�� 
         */ 
        public void copyImageFromAnotherDoc(String anotherDocPath, int shapeIndex, 
                        String pos) { 
                Dispatch doc2 = null; 
                try { 
                        doc2 = Dispatch.call(documents, "Open", anotherDocPath) 
                                        .toDispatch(); 
                        Dispatch shapes = Dispatch.get(doc2, "InLineShapes").toDispatch(); 
                        Dispatch shape = Dispatch.call(shapes, "Item", 
                                        new Variant(shapeIndex)).toDispatch(); 
                        Dispatch imageRange = Dispatch.get(shape, "Range").toDispatch(); 
                        Dispatch.call(imageRange, "Copy"); 
                        if (this.find(pos)) { 
                                Dispatch textRange = Dispatch.get(selection, "Range") 
                                                .toDispatch(); 
                                Dispatch.call(textRange, "Paste"); 
                        } 
                } catch (Exception e) { 
                        e.printStackTrace(); 
                } finally { 
                        if (doc2 != null) { 
                                Dispatch.call(doc2, "Close", new Variant(saveOnExit)); 
                                doc2 = null; 
                        } 
                } 
        } 

        /** *//** 
         * ������� 
         *     
         * @param pos    λ�� 
         * @param cols ���� 
         * @param rows ���� 
         */ 
        public void createTable(int numCols, int numRows){//(String pos, int numCols, int numRows) { 
//                if (!find(pos)) { 
                        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); 
                        Dispatch range = Dispatch.get(selection, "Range").toDispatch(); 
                        Dispatch newTable = Dispatch.call(tables, "Add", range, 
                                        new Variant(numRows), new Variant(numCols)).toDispatch(); 
                        Dispatch.call(selection, "MoveRight"); 
                        moveEnd(); 
//                } 
        } 

        /** *//** 
         * ��ָ����ǰ�������� 
         *     
         * @param tableIndex word�ļ��еĵ�N�ű�(��1��ʼ) 
         * @param rowIndex ָ���е����(��1��ʼ) 
         */ 
        public void addTableRow(int tableIndex, int rowIndex) { 
                // ���б�� 
                Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); 
                // Ҫ���ı�� 
                Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)) 
                                .toDispatch(); 
                // ���������� 
                Dispatch rows = Dispatch.get(table, "Rows").toDispatch(); 
                Dispatch row = Dispatch.call(rows, "Item", new Variant(rowIndex)) 
                                .toDispatch(); 
                Dispatch.call(rows, "Add", new Variant(row)); 
        } 

        /** *//** 
         * �ڵ�1��ǰ����һ�� 
         *     
         * @param tableIndex word�ĵ��еĵ�N�ű�(��1��ʼ) 
         */ 
        public void addFirstTableRow(int tableIndex) { 
                // ���б�� 
                Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); 
                // Ҫ���ı�� 
                Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)) 
                                .toDispatch(); 
                // ���������� 
                Dispatch rows = Dispatch.get(table, "Rows").toDispatch(); 
                Dispatch row = Dispatch.get(rows, "First").toDispatch(); 
                Dispatch.call(rows, "Add", new Variant(row)); 
        } 

        /** *//** 
         * �����1��ǰ����һ�� 
         *     
         * @param tableIndex 
         *                        word�ĵ��еĵ�N�ű�(��1��ʼ) 
         */ 
        public void addLastTableRow(int tableIndex) { 
                // ���б�� 
                Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); 
                // Ҫ���ı�� 
                Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)) 
                                .toDispatch(); 
                // ���������� 
                Dispatch rows = Dispatch.get(table, "Rows").toDispatch(); 
                Dispatch row = Dispatch.get(rows, "Last").toDispatch(); 
                Dispatch.call(rows, "Add", new Variant(row)); 
        } 

        /** *//** 
         * ����һ�� 
         *     
         * @param tableIndex word�ĵ��еĵ�N�ű�(��1��ʼ) 
         */ 
        public void addRow(int tableIndex) { 
                Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); 
                // Ҫ���ı�� 
                Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)) 
                                .toDispatch(); 
                // ���������� 
                Dispatch rows = Dispatch.get(table, "Rows").toDispatch(); 
                Dispatch.call(rows, "Add"); 
        } 

        /** *//** 
         * ����һ�� 
         *     
         * @param tableIndex word�ĵ��еĵ�N�ű�(��1��ʼ) 
         */ 
        public void addCol(int tableIndex) { 
                // ���б�� 
                Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); 
                // Ҫ���ı�� 
                Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)) 
                                .toDispatch(); 
                // ���������� 
                Dispatch cols = Dispatch.get(table, "Columns").toDispatch(); 
                Dispatch.call(cols, "Add").toDispatch(); 
                Dispatch.call(cols, "AutoFit"); 
        } 

        /** *//** 
         * ��ָ����ǰ�����ӱ����� 
         *     
         * @param tableIndex word�ĵ��еĵ�N�ű�(��1��ʼ) 
         * @param colIndex    ָ���е���� (��1��ʼ) 
         */ 
        public void addTableCol(int tableIndex, int colIndex) { 
                // ���б�� 
                Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); 
                // Ҫ���ı�� 
                Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)) 
                                .toDispatch(); 
                // ���������� 
                Dispatch cols = Dispatch.get(table, "Columns").toDispatch(); 
                System.out.println(Dispatch.get(cols, "Count")); 
                Dispatch col = Dispatch.call(cols, "Item", new Variant(colIndex)) 
                                .toDispatch(); 
                // Dispatch col = Dispatch.get(cols, "First").toDispatch(); 
                Dispatch.call(cols, "Add", col).toDispatch(); 
                Dispatch.call(cols, "AutoFit"); 
        } 

        /** *//** 
         * �ڵ�1��ǰ����һ�� 
         *     
         * @param tableIndex word�ĵ��еĵ�N�ű�(��1��ʼ) 
         */ 
        public void addFirstTableCol(int tableIndex) { 
                Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); 
                // Ҫ���ı�� 
                Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)) 
                                .toDispatch(); 
                // ���������� 
                Dispatch cols = Dispatch.get(table, "Columns").toDispatch(); 
                Dispatch col = Dispatch.get(cols, "First").toDispatch(); 
                Dispatch.call(cols, "Add", col).toDispatch(); 
                Dispatch.call(cols, "AutoFit"); 
        } 

        /** *//** 
         * �����һ��ǰ����һ�� 
         *     
         * @param tableIndex word�ĵ��еĵ�N�ű�(��1��ʼ) 
         */ 
        public void addLastTableCol(int tableIndex) { 
                Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); 
                // Ҫ���ı�� 
                Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)) 
                                .toDispatch(); 
                // ���������� 
                Dispatch cols = Dispatch.get(table, "Columns").toDispatch(); 
                Dispatch col = Dispatch.get(cols, "Last").toDispatch(); 
                Dispatch.call(cols, "Add", col).toDispatch(); 
                Dispatch.call(cols, "AutoFit"); 
        } 

        /** *//** 
         * �Զ�������� 
         *     
         */ 
        public void autoFitTable() { 
                Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); 
                int count = Dispatch.get(tables, "Count").toInt(); 
                for (int i = 0; i< count; i++) { 
                        Dispatch table = Dispatch.call(tables, "Item", new Variant(i + 1)) 
                                        .toDispatch(); 
                        Dispatch cols = Dispatch.get(table, "Columns").toDispatch(); 
                        Dispatch.call(cols, "AutoFit"); 
                } 
        } 

        /** *//** 
         * ����word��ĺ��Ե������Ŀ��,���к걣����document�� 
         *     
         */ 
        public void callWordMacro() { 
                Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); 
                int count = Dispatch.get(tables, "Count").toInt(); 
                Variant vMacroName = new Variant("Normal.NewMacros.tableFit"); 
                Variant vParam = new Variant("param1"); 
                Variant para[] = new Variant[] { vMacroName }; 
                for (int i = 0; i < para.length; i++) { 
                        Dispatch table = Dispatch.call(tables, "Item", new Variant(i + 1)) 
                                        .toDispatch(); 
                        Dispatch.call(table, "Select"); 
                        Dispatch.call(word, "Run", "tableFitContent"); 
                } 
        } 

        /** *//** 
         * ���õ�ǰѡ�����ݵ����� 
         *     
         * @param boldSize 
         * @param italicSize 
         * @param underLineSize �»��� 
         * @param colorSize ������ɫ 
         * @param size �����С 
         * @param name �������� 
         */ 
        public void setFont(boolean bold, boolean italic, boolean underLine, 
                        String colorSize, String size, String name) { 
                Dispatch font = Dispatch.get(selection, "Font").toDispatch(); 
                Dispatch.put(font, "Name", new Variant(name)); 
                Dispatch.put(font, "Bold", new Variant(bold)); 
                Dispatch.put(font, "Italic", new Variant(italic)); 
                Dispatch.put(font, "Underline", new Variant(underLine)); 
                Dispatch.put(font, "Color", colorSize); 
                Dispatch.put(font, "Size", size); 
        } 

        /** *//** 
         * �ļ���������Ϊ 
         *     
         * @param savePath ��������Ϊ·�� 
         */ 
        public void save(String savePath) { 
                Dispatch.call( 
                                (Dispatch) Dispatch.call(word, "WordBasic").getDispatch(), 
                                "FileSaveAs", savePath); 
        } 

        /** *//** 
         * �رյ�ǰword�ĵ� 
         *     
         */ 
        public void closeDocument() { 
                if (doc != null) { 
                        Dispatch.call(doc, "Save"); 
                        Dispatch.call(doc, "Close", new Variant(saveOnExit)); 
                        doc = null; 
                } 
        } 

        /** *//** 
         * �ر�ȫ��Ӧ�� 
         *     
         */ 
        public void close() { 
                closeDocument(); 
                if (word != null) { 
                        Dispatch.call(word, "Quit"); 
                        word = null; 
                } 
                selection = null; 
                documents = null; 
        } 

        /** *//** 
         * ��ӡ��ǰword�ĵ� 
         *     
         */ 
        public void printFile() { 
                if (doc != null) { 
                        Dispatch.call(doc, "PrintOut"); 
                } 
        } 

//        public static void main(String args[])throws Exception { 
//     
//                MSWordManager msWordManager = new MSWordManager(true); 
//                msWordManager.createNewDocument();                             
//                msWordManager.insertText("aaaaaaaaaaaaaaaaaaaaa"); 
//                msWordManager.moveEnd();       
//                msWordManager.close(); 
//        } 
         
         
}
