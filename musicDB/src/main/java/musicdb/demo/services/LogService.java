package musicdb.demo.services;

import musicdb.demo.models.serviceModels.LogServiceModel;

import java.util.List;

public interface LogService {
    void createLog(Long albumId, String action);

    List<LogServiceModel> getAllLogs();
}
