package plugin.files_manager.implement;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import plugin.files_manager.exceptions.FileException;
import plugin.files_manager.exceptions.FileExceptionEnum;

/**
 * Clase para el manejo de archivos
 * 
 * @author javiersolanop777
 */
public class FileManager {
    
    // Properties:
    private String atrPath;

    // Constructors:
    public FileManager(String prmPath)
    {
        atrPath = prmPath;
    }

    // Methods:

    /**
     *  Metodo para exportar un archivo en formato 'txt',
     *  adicionando un salto de linea a cada fila del arreglo.
     * 
     *  @param prmFileName El nombre que debe tener el archivo
     *  @param prmContent El contenido del archivo
     * 
     *  @throws FileException Si no se puede exportar
     */
    public void exportTxtWithLn(String prmFileName, String[] prmContent) throws FileException
    {
        try(FileOutputStream objFileOutput = new FileOutputStream(atrPath+"\\"+prmFileName+".txt"))
        {
            String[] varContent = prmContent.clone();

            for(String str: varContent){
                str = str.concat("\n");
                objFileOutput.write(str.getBytes(StandardCharsets.UTF_8));
            }
            objFileOutput.flush();
        }
        catch(IOException e){
            FileException.throwException(FileExceptionEnum.EXPORT);
        }
    }
    
    /**
     *  Metodo para exportar un archivo en formato 'txt'
     * 
     *  @param prmFileName El nombre que debe tener el archivo
     *  @param prmContent El contenido del archivo
     * 
     *  @throws FileException Si no se puede exportar
     */
    public void exportTxt(String prmFileName, String[] prmContent) throws FileException
    {
        try(FileOutputStream objFileOutput = new FileOutputStream(atrPath+"\\"+prmFileName+".txt"))
        {
            String[] varContent = prmContent.clone();

            for(String str: varContent)
                objFileOutput.write(str.getBytes(StandardCharsets.UTF_8));
            objFileOutput.flush();
        }
        catch(IOException e){
            FileException.throwException(FileExceptionEnum.EXPORT);
        }
    }

    /**
     *  Metodo para exportar un archivo en formato 'jpeg'
     * 
     *  @param prmFileName El nombre que debe tener el archivo
     *  @param prmImage El contenido del archivo
     * 
     *  @throws FileException Si no se puede exportar
     */
    public void exportJpeg(String prmFileName, byte[] prmImage) throws FileException
    {
        try(FileOutputStream objFileOutput = new FileOutputStream(atrPath + "\\" + prmFileName + ".jpeg"))
        {
            objFileOutput.write(prmImage);
            objFileOutput.flush();
        }
        catch(IOException e)
        {
            FileException.throwException(FileExceptionEnum.EXPORT);
        }
    }
    
    /**
     *  Metodo para exportar un archivo en formato 'pdf'
     * 
     *  @param prmFileName El nombre que debe tener el archivo
     *  @param prmPdf El contenido del archivo
     * 
     *  @throws FileException Si no se puede exportar
     */
    public void exportPdf(String prmFileName, byte[] prmPdf) throws FileException
    {
        try(FileOutputStream objFileOutput = new FileOutputStream(atrPath + "\\" + prmFileName + ".pdf"))
        {
            objFileOutput.write(prmPdf);
            objFileOutput.flush();
        }
        catch(IOException e)
        {
            FileException.throwException(FileExceptionEnum.EXPORT);
        }
    }
    
    /**
     *  Metodo para exportar varios pdf en un solo archivo
     * 
     *  @param prmFileName El nombre que debe tener el archivo
     *  @param prmPdfs El contenido de los pdf
     * 
     *  @throws FileException Si no se puede exportar
     */
    public void exportPdf(String prmFileName, byte[][] prmPdfs) throws FileException
    {
        if(prmPdfs == null) return;
                
        try
        {
            PDFMergerUtility objPDFMerger = new PDFMergerUtility();
            objPDFMerger.setDestinationFileName(atrPath + "\\" + prmFileName + ".pdf");
           
            for(byte[] arrPdf: prmPdfs)
               objPDFMerger.addSource(new ByteArrayInputStream(arrPdf));
            objPDFMerger.mergeDocuments(null);
        }
        catch(IOException e)
        {
            FileException.throwException(FileExceptionEnum.EXPORT);
        }
    }

    /**
     *  Metodo para importar un archivo en formato 'txt'
     * 
     *  @param prmFileName El nombre del archivo
     *  @return El arreglo de filas del archivo o null si esta vacio
     * 
     *  @throws FileException Si no se puede importar
     */
    public String[] importTxt(String prmFileName) throws FileException
    {
        try(
            FileInputStream objFileInput = new FileInputStream(atrPath+"\\"+prmFileName+".txt");
            DataInputStream objDataInputStream = new DataInputStream(objFileInput);
        )
        {
            byte[] arrBytes = new byte[objFileInput.available()];
            objDataInputStream.readFully(arrBytes);            
            objFileInput.close();
            
            if(arrBytes.length > 0)
            {
                byte[][] mtrBytes = ByteManager.splitln(arrBytes);
                String[] arrRows;

                if(mtrBytes != null)
                {
                    int varSize = mtrBytes.length;
                    arrRows = new String[varSize];

                    for(int i = 0; i < varSize; i++)
                        arrRows[i] = ByteManager.parseByteToString(mtrBytes[i]);
                }
                else
                {
                    arrRows = new String[1];
                    arrRows[0] = ByteManager.parseByteToString(arrBytes)
                                            .replace("\n", "");
                }
                return arrRows;
            }
        }
        catch(IOException e)
        {
            FileException.throwException(FileExceptionEnum.IMPORT);
        }
        return null;
    }

    /**
     *  Metodo para importar  un archivo en formato 'jpeg'
     * 
     *  @param prmFileName El nombre del archivo
     *  @return El arreglo de bytes del archivo o null si esta vacio
     * 
     *  @throws FileException Si no se puede importar
     */
    public byte[] importJpeg(String prmFileName) throws FileException
    {
        try(
            FileInputStream objFileInput = new FileInputStream(atrPath+"\\"+prmFileName+".jpeg");
            DataInputStream objDataInputStream = new DataInputStream(objFileInput);
        )
        {
            byte[] arrBytes = new byte[objFileInput.available()];
            objDataInputStream.readFully(arrBytes);
            return arrBytes;
        }
        catch(IOException e)
        {
            FileException.throwException(FileExceptionEnum.IMPORT);
        }
        return null;
    }
    
    /**
     *  Metodo para importar  un archivo en formato 'pdf'
     * 
     *  @param prmFileName El nombre del archivo
     *  @return El arreglo de bytes del archivo o null si esta vacio
     * 
     *  @throws FileException Si no se puede importar
     */
    public byte[] importPdf(String prmFileName) throws FileException
    {
        try(
            FileInputStream objFileInput = new FileInputStream(atrPath+"\\"+prmFileName+".pdf");
            DataInputStream objDataInputStream = new DataInputStream(objFileInput);
        )
        {
            byte[] arrBytes = new byte[objFileInput.available()];
            objDataInputStream.readFully(arrBytes);
            return arrBytes;
        }
        catch(IOException e)
        {
            FileException.throwException(FileExceptionEnum.IMPORT);
        }
        return null;  
    }

    /**
     *  Metodo para remover un archivo en formato 'jpeg'
     * 
     *  @param prmFileName El nombre del archivo
     * 
     *  @throws FileException Si no se puede remover
     */
    public void removeJpeg(String prmFileName) throws FileException
    {
        try
        {
            File objFile = new File(atrPath+"\\"+prmFileName+".jpeg");

            if(!objFile.delete()) FileException.throwException(FileExceptionEnum.DELETE);
        }
        catch(FileException e){
            FileException.throwException(FileExceptionEnum.DELETE);
        }
    }

    /**
     *  Metodo para remover un archivo en formato 'pdf'
     * 
     *  @param prmFileName El nombre del archivo
     * 
     *  @throws FileException Si no se puede remover
     */
    public void removePdf(String prmFileName) throws FileException
    {
        try
        {
            File objFile = new File(atrPath+"\\"+prmFileName+".pdf");

            if(!objFile.delete()) FileException.throwException(FileExceptionEnum.DELETE);
        }
        catch(FileException e){
            FileException.throwException(FileExceptionEnum.DELETE);
        }
    }
}
