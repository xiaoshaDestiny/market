package com.rbxu.market.domain.manager;

import com.rbxu.market.domain.model.ProjectModel;

public interface ProjectManager {

    Boolean create(ProjectModel projectModel);

    ProjectModel getById(Long id);
}
