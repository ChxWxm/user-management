package com.usermangement.wallet;

import com.usermangement.exception.DuplicationException;
import com.usermangement.exception.InternalServiceException;
import com.usermangement.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    private final List<Wallet> wallets = new ArrayList<>(List.of(
            new Wallet(1, "Jack", "jack@gmail.com"),
            new Wallet(2, "Alice", "alice@gmail.com")
    ));

    List<Wallet> getWallets(Optional<String> name) {
        try {
            //  callNormalService();
            if (name.isPresent()) {
                return wallets.stream().filter(w -> w.getName().equals(name.get())).toList();
            }
            return wallets;
        } catch (Exception e) {
            throw new InternalServiceException("Internal service exception with Normal service");
        }
    }

    Wallet createWallet(WalletRequest request) {
        //  This is the way to use lamda method
        //  Optional<Integer> maxId = wallets.stream().map(Wallet::getId).max(Integer::compareTo);
        System.out.println("email: " + request.email());
        Optional<Wallet> duplicateWallet = wallets.stream()
                .filter(wallet -> wallet.getEmail().equals(request.email()))
                .findFirst();
        if (duplicateWallet.isPresent()) {
            throw new DuplicationException("Wallet email " + request.email() + " already exist.");
        }

        Optional<Integer> maxId = wallets.stream().map(w -> w.getId()).max((idA, idB) -> idA.compareTo(idB));
        Integer currentId = maxId.orElse(0) + 1;
        Wallet wallet = new Wallet(currentId, request.name(), request.email());
        wallets.add(wallet);
        return wallet;
    }

    Wallet getWalletById(Integer id) {
        return wallets.stream().filter(w -> w.getId() == id).findFirst().orElseThrow(() -> new NotFoundException("Wallet Not found by id " + id));
    }

    private void callNormalService() {
        throw new RuntimeException();
    }
}
