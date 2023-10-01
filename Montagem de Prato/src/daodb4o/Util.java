package daodb4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import modelo.Prato;
import modelo.Carne;
import modelo.Acompanhamento;

public class Util {
	private static ObjectContainer manager=null;

	public static ObjectContainer conectarBanco(){
		if (manager != null)
			return manager;		//ja tem uma conexao

		//---------------------------------------------------------------
		//configurar, criar e abrir banco local (pasta do projeto)
		//---------------------------------------------------------------
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // mensagens na tela 0(desliga),1,2,3...
		
		// habilitar cascata para alterar, apagar e recuperar objetos
		config.common().objectClass(Prato.class).cascadeOnDelete(false);
		config.common().objectClass(Prato.class).cascadeOnUpdate(true);
		config.common().objectClass(Prato.class).cascadeOnActivate(true);
		config.common().objectClass(Carne.class).cascadeOnDelete(true);
		config.common().objectClass(Carne.class).cascadeOnUpdate(true);
		config.common().objectClass(Carne.class).cascadeOnActivate(true);
		config.common().objectClass(Acompanhamento.class).cascadeOnDelete(false);;
		config.common().objectClass(Acompanhamento.class).cascadeOnUpdate(true);;
		config.common().objectClass(Acompanhamento.class).cascadeOnActivate(true);
		
		//conexao local
		manager = Db4oEmbedded.openFile(config, "banco.db4o");
		return manager;
	}
	
	public static void desconectar() {
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}
}
