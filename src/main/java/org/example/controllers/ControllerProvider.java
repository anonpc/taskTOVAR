package org.example.controllers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.annotation.Inject;

@RequiredArgsConstructor
@Getter
public class ControllerProvider {
    @Inject
    private final DepartmentController departmentController;
    @Inject
    private final ItemController itemController;
}
