package org.example.Service;

import org.example.Model.Target;
import org.example.Model.Transaction;

import java.time.LocalDate;
import java.util.*;

public class TargetService {
    private static Map<String, List<Target>> usersTargets = new HashMap<>();

    public static void addTarget(String userId, double targetAmount, String description){
        String id = UUID.randomUUID().toString();
        Target target = new Target(id, userId, targetAmount, 0, description);
        usersTargets.computeIfAbsent(userId, k -> new ArrayList<>()).add(target);
    }

    public static void updateSavedAmount(String userId, String targetId, double amount){
        List<Target> targets = usersTargets.get(userId);
        if (targets != null){
            for (Target target : targets){
                if(target.getId().equals(targetId)){
                    target.setSavedAmount(target.getSavedAmount()+amount);
                    return;
                }
            }
        }
    }

    public static void removeTarget(String userId, String targetId){
        List<Target> targets = usersTargets.get(userId);
        if (targets != null){
            targets.removeIf(target -> target.getId().equals(targetId));
            if (targets.isEmpty()){
                usersTargets.remove(userId);
            }
        }
    }

    public static List<Target> getUserTargets(String userId){
        return usersTargets.getOrDefault(userId, Collections.emptyList());
    }

    public static boolean isTargetRich(String userId, String targetId){
        List<Target> targets = usersTargets.get(userId);
        if (targets != null){
            for (Target target : targets){
                if (target.getId().equals(targetId)){
                    return target.getSavedAmount() >= target.getTargetAmount();
                }
            }
        }
        return false;
    }
}
