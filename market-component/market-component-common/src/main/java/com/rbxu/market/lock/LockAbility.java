package com.rbxu.market.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
public class LockAbility implements DBAbility {

    private final CommonRecordSupport<LockRecordDTO> recordSupport;

    public LockAbility(CommonRecordSupport<LockRecordDTO> recordSupport) {
        this.recordSupport = recordSupport;
    }

    public Lock lock(String key) {
        if (Objects.isNull(key)) {
            log.info("key为空，不可加锁");
            return new Lock(false, null, null);
        }

        String uuid = UUID.randomUUID().toString();

        List<LockRecordDTO> dtos = recordSupport.getByUniqueKey(key);
        if (Objects.isNull(dtos)) {
            LockRecordDTO save = new LockRecordDTO();
            save.setKey(key);
            save.setType(this.type().name());
            save.setValue(uuid);
            Boolean crate = false;
            try {
                 crate = recordSupport.create(save);
            } catch (Exception e) {
                log.info("DB 创建锁失败");
            }
            if (crate) {
                dtos = recordSupport.getByUniqueKey(key);
                if (Objects.isNull(dtos)) {
                    return new Lock(false, uuid, key);
                }

                if (dtos.size() == 1 && dtos.get(0).getValue().equals(uuid)) {
                    return new Lock(true, uuid, key);
                } else {
                    dtos.forEach(recordSupport::delete);
                    return new Lock(false, uuid, key);
                }
            }
        }
        return new Lock(false, uuid, key);
    }

    public Boolean unlock(Lock lock) {
        if (Objects.isNull(lock)
                || Objects.isNull(lock.getUuid())
                || Boolean.FALSE.equals(lock.getLocked())) {
            log.info("lock为空，不可解锁");
            return false;
        }
        List<LockRecordDTO> dtos = recordSupport.getByUniqueKey(lock.getKey());
        if (Objects.isNull(dtos)) {
            log.info("不知道被谁删了");
            return true;
        }


        dtos.stream().filter(dto -> dto.getValue().equals(lock.getUuid())).forEach(dto -> {
            recordSupport.delete(dtos.get(0));
        });
        return true;
    }


    @Override
    public AbilityType type() {
        return AbilityType.LOCK;
    }



    public static class Lock {

        private Boolean locked;

        private String uuid;

        private String key;

        public Lock(Boolean locked, String uuid, String key) {
            this.locked = locked;
            this.uuid = uuid;
            this.key = key;
        }

        public Boolean getLocked() {
            return locked;
        }

        public String getUuid() {
            return uuid;
        }

        public String getKey() {
            return key;
        }

        @Override
        public String toString() {
            return "Lock{" +
                    "locked=" + locked +
                    ", uuid='" + uuid + '\'' +
                    ", key='" + key + '\'' +
                    '}';
        }
    }
}
