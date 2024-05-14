import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class List {

    public static void main(String[] args) {
        int cont = 0;
        int totalEdad = 0;
        double totalNota = 0;
        double notaMinima = Double.MAX_VALUE;
        double notaMaxima = Double.MIN_VALUE;

        try {
            FileReader fileReader = new FileReader("src/lista_alumnos.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                if (cont == 0) {
                    cont++;
                    continue;
                }

                String[] partes = linea.split(",");
                
                if (partes.length < 5) {
                    
                    System.out.println("La línea no tiene la cantidad esperada de elementos: " + linea);
                    continue;
                }

                int edad = Integer.parseInt(partes[3].trim());
                totalEdad += edad;

                double nota = Double.parseDouble(partes[4].trim());
                totalNota += nota;

                notaMaxima = Math.max(notaMaxima, nota);
                notaMinima = Math.min(notaMinima, nota);

                cont++;
            }

            bufferedReader.close();

            int numeroEstudiantes = cont - 1; 

            int edadPromedio = totalEdad / numeroEstudiantes; 

            double notaMedia = totalNota / numeroEstudiantes; 

            
            FileWriter fileWriter = new FileWriter("src/lista_alumnos.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
           
            
            bufferedWriter.newLine(); 
            bufferedWriter.write(String.valueOf(numeroEstudiantes));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(edadPromedio));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(notaMedia));

            bufferedWriter.close(); 

            System.out.println("Datos agregados al archivo exitosamente.");

            
            FileWriter informeFileWriter = new FileWriter("informe-clase.txt");
            BufferedWriter informeBufferedWriter = new BufferedWriter(informeFileWriter);

            
            informeBufferedWriter.write("Número de estudiantes: " + numeroEstudiantes);
            informeBufferedWriter.newLine();
            informeBufferedWriter.write("Edad promedio de los estudiantes: " + edadPromedio);
            informeBufferedWriter.newLine();
            informeBufferedWriter.write("Nota media de los estudiantes: " + notaMedia);

            informeBufferedWriter.close(); 

            System.out.println("Informe creado exitosamente.");

        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer o escribir en el archivo.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir la edad o la nota a un número.");
            e.printStackTrace();
        } catch (ArithmeticException e) {
            System.out.println("Error al calcular la edad promedio o la nota media.");
            e.printStackTrace();
        }
    }
}

