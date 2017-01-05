package core;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class DatabasePing {
	
	public static void userRegistration(String email, String password){
		Backendless.initApp( "8662F7F0-FA42-2800-FFDB-8A331467EF00", "21B58D09-56A2-3158-FF75-EB1B1237E500", "v1" );
		  BackendlessUser user = new BackendlessUser();
		  user.setProperty( "email", email.toString() );
		  user.setPassword( password.toString() );
		   
		  Backendless.UserService.register( user, new AsyncCallback<BackendlessUser>()
		  {

			@Override
			public void handleFault(BackendlessFault bFault) {
				// TODO Auto-generated method stub
				System.out.println("Error " +bFault.getMessage());
				bFault.getMessage();
				
			}

			@Override
			public void handleResponse(BackendlessUser bUser) {
				// TODO Auto-generated method stub
				
				System.out.print(bUser + "");
				
			}
		  
		     
		   
		  } );
		  
	}
}
