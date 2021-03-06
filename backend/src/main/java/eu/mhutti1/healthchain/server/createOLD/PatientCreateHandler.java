package eu.mhutti1.healthchain.server.createOLD;


import eu.mhutti1.healthchain.roles.IdentityOwner;
import eu.mhutti1.healthchain.roles.Role;
import eu.mhutti1.healthchain.roles.TrustAnchor;
import eu.mhutti1.healthchain.server.createOLD.CreateHandler;
import org.hyperledger.indy.sdk.IndyException;
import org.hyperledger.indy.sdk.wallet.Wallet;

import java.util.concurrent.ExecutionException;


/**
 * Created by jedraz on 25/10/2018.
 */
public class PatientCreateHandler extends CreateHandler {

  @Override
  public Role createVerifier(Wallet wallet, String did, String verKey) {
    return new TrustAnchor(wallet, did, verKey);
  }

  @Override
  public Role createAccountHolder(Role role, String did, String walletId, String walletKey) throws InterruptedException, ExecutionException, IndyException {
    return new IdentityOwner(role, did, walletId, walletKey);
  }
}
