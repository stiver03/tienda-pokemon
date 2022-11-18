/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemones;

import DAOpokemones.DAOpokemones;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dafer
 */
public class PokemonesController implements DAOpokemones{
    
    private static PokemonesController instancia;
    private String pokemones[][];
    private int cPokemon;
    private boolean pokemonEliminado;
    
    public static PokemonesController getInstance(){
        if(instancia == null)
            instancia = new PokemonesController();
        return instancia;
    }
    
    private PokemonesController(){
        this.pokemones=new String[6][100];
        this.cPokemon=0;
        pokemonEliminado=false;
    }
    
    @Override
    public boolean GuardarPokemon(String cpokemon,String npokemon, String tpokemon, String spokemon, String vpokemon, String epokemon){
        
        pokemones[0][cPokemon]=cpokemon;
        pokemones[1][cPokemon]=npokemon;
        pokemones[2][cPokemon]=tpokemon;
        pokemones[3][cPokemon]=spokemon;
        pokemones[4][cPokemon]=vpokemon;
        pokemones[5][cPokemon]=epokemon;
        cPokemon++;
        
        return true;
    }
    
    @Override
    public boolean GuardarPokemon(String[] pokemon) {
        pokemones[0][cPokemon]=pokemon[0];
        pokemones[1][cPokemon]=pokemon[1];
        pokemones[2][cPokemon]=pokemon[2];
        pokemones[3][cPokemon]=pokemon[3];
        pokemones[4][cPokemon]=pokemon[4];
        pokemones[5][cPokemon]=pokemon[5];
        cPokemon++;
        
        return true;
    }

    @Override
    public DefaultTableModel MostrarPokemones() {
        DefaultTableModel modelo = new DefaultTableModel();
        
        //creo los encabezados de la tabla
        modelo.addColumn("codigo");
        modelo.addColumn("pokemon");
        modelo.addColumn("Tipo");
        modelo.addColumn("Sexo");
        modelo.addColumn("Variocolor");
        modelo.addColumn("Estadisticas");
        
        //agregando filas al modelo
        
        for(int i=0;i<cPokemon;i++){
            String[] pokemon=new String[6];
            for(int j=0;j<6;j++){
                //System.out.println("codigo "+pokemones[i][j]+": nombre: "+pokemones[i][j]);
                if(pokemones[j][i] != null)
                    pokemon[j]=pokemones[j][i];
            }
            modelo.addRow(pokemon);
        }
        return modelo;
    }

    @Override
    public boolean ActualizarPokemon(String[] pokemon) {
        int fila=0;
        for(int i=0;i<cPokemon;i++){
            if(pokemones[0][i]==pokemon[0]){
                fila=i;
                break;
            }
        }
        pokemones[1][fila]=pokemon[1];
        pokemones[2][fila]=pokemon[2];
        pokemones[3][fila]=pokemon[3];
        pokemones[4][fila]=pokemon[4];
        pokemones[5][fila]=pokemon[5];
        return true;
    }

    @Override
    public void EliminarPokemon(int codigo) {
         if( cPokemon>=1)
       {
            
            int seleccion = JOptionPane.showConfirmDialog(null,"seguro desea eliminar el pokemon de nombre: "+pokemones[1][codigo], "confirmacion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            System.out.println(seleccion);
            if(seleccion==0)
            {
                for (int i=codigo;i<100;i++)
                {
                    for(int j=0;j<6;j++)
                    {
                
                        if(pokemones[j][i] !=null)
                        {
                            pokemones[j][i]=pokemones[j][codigo+1];   
                        }
                    }
                    codigo++;
                }
                cPokemon--;
                JOptionPane.showMessageDialog(null,"eliminado exitoso");
            }
        }
       
       else
       {
            JOptionPane.showMessageDialog(null, "no hay pokemones para eliminar");   
       }
    }

    
}
