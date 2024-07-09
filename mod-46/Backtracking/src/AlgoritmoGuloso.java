import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgoritmoGuloso {

    public interface Batch{
        int[] getFileSize();
        int getTapeSize();
    }

    private static class NewBatch implements Batch{

        @Override
        public int[] getFileSize() {
            return new int[] {
                    70, 10, 20
            };
        }

        @Override
        public int getTapeSize() {
            return 100;
        }
    }

    private class Tape{
        private int stored;
        private int numberOfFiles;

        public Tape(int stored) {
            this.stored = stored;
            this.numberOfFiles = 1;
        }

        public int getStored() {
            return stored;
        }

        public int getNumberOfFiles() {
            return numberOfFiles;
        }

       public void addStorage(int storage){
            this.stored += storage;
       }

       public void addFiles(){
            this.numberOfFiles++;
       }
    }

    public int getMinimumTapeCount(final Batch batch){
        //Ordenando o array de tamanhos de arquivos
        int[] sizes = batch.getFileSize();
        Arrays.sort(sizes);

        int tapes = 0;
        List<Tape> tapesList = new ArrayList<Tape>();

        for(int i = sizes.length - 1; i > -1; i--){
            boolean stored = false;

            for(Tape tape : tapesList){
                if(tape.getStored() + sizes[i] == batch.getTapeSize() && (tape.getNumberOfFiles() < 2)){
                    tape.addStorage(sizes[i]);
                    tape.addFiles();
                    stored = true;
                    break;
                }
            }

            if(!stored){
                tapes++;
                tapesList.add(new Tape(sizes[i]));
            }
        }

        return tapes;
    }

    public static void main(String[] args) {
        AlgoritmoGuloso a = new AlgoritmoGuloso();
        Batch batch = new NewBatch();

        System.out.println(a.getMinimumTapeCount(batch));
    }

}
