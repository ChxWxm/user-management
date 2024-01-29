package com.usermangement.wallet;
import com.usermangement.exception.BadRequestException;
import com.usermangement.profile.Profile;
import com.usermangement.profile.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final ProfileRepository profileRepository;

    public WalletService(WalletRepository walletRepository, ProfileRepository profileRepository) {
        this.walletRepository = walletRepository;
        this.profileRepository = profileRepository;
    }

    List<Wallet> getWallets() {
        return walletRepository.findAll();
    }

    @Transactional // make sure if one of operation in this code failed, all data must not be saved in db
    Wallet createWallet(WalletRequest request) throws Exception {
        Optional<Profile> profileOptional = profileRepository.findByEmail(request.email());
        Profile profile;
        if (profileOptional.isPresent()) {
            profile = profileOptional.get();
        } else {
            profile = new Profile();
            profile.setName(request.name());
            profile.setEmail(request.email());
            profileRepository.save(profile);
        }

        Wallet wallet = new Wallet();
        wallet.setName(request.name());
        wallet.setActive(true);
        wallet.setProfile(profile);
        walletRepository.save(wallet);
        return wallet;
    }

    Wallet getWalletById(Integer id) {
        Optional<Wallet> walletOptional = walletRepository.findById(Long.valueOf(id));
        if (walletOptional.isEmpty()) {
            throw new BadRequestException("Wallet id " + id + " not found.");
        }
        return walletOptional.get();
    }

    Wallet updateWalletById(Integer id, UpdateWalletRequest request) {
        Optional<Wallet> walletOptional = walletRepository.findById(Long.valueOf(id));
        if (walletOptional.isEmpty()) {
            throw new BadRequestException("Wallet id " + id + " not found.");
        }
        Wallet wallet = walletOptional.get();
        wallet.setName(request.name());
        walletRepository.save(wallet);
        return wallet;
    }

    void deleteById(Integer id) {
        walletRepository.deleteById(Long.valueOf(id));
    }

    void activeAllWallet() {
        walletRepository.setAllWalletActive();
    }

    void deleteAllWalletIdMoreThan2() {
        walletRepository.deleteAllWalletIdMoreThan2();
    }
}
