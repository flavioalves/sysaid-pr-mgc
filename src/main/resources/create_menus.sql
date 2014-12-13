INSERT INTO ui_menus (mi_id, mi_name, mi_parent, mi_action_type, mi_action, mi_enabled, mi_ui_class, mi_module_id, mi_order, mi_permission, mi_edition) VALUES
(300, 'Gestão de OS', 1, 'url', '#', 'Y', 'assets', NULL, 0, 0, 0);

INSERT INTO ui_menus (mi_id, mi_name, mi_parent, mi_action_type, mi_action, mi_enabled, mi_ui_class, mi_module_id, mi_order, mi_permission, mi_edition) VALUES
(301, 'Suporte', 300, 'url', '#', 'Y', NULL, NULL, 0, 0, 0);

INSERT INTO ui_menus (mi_id, mi_name, mi_parent, mi_action_type, mi_action, mi_enabled, mi_ui_class, mi_module_id, mi_order, mi_permission, mi_edition) VALUES
(302, 'Lista de Ordem de Serviço', 301, 'url', '#/mgd/listaos.html', 'Y', NULL, NULL, 0, 1024, 0);

INSERT INTO ui_menus (mi_id, mi_name, mi_parent, mi_action_type, mi_action, mi_enabled, mi_ui_class, mi_module_id, mi_order, mi_permission, mi_edition) VALUES
(303, 'Grupo Dinamico', 301, 'url', '#/mgd/grupodinamico.html', 'Y', NULL, NULL, 0, 1024, 0);

