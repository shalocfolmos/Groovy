<g:each in="${commonElementGroupCollection}" var="commonElementGroup">
    <li>
        <a href="" onclick="applyCommonElementGroup(${commonElementGroup.id},${templateId})">${commonElementGroup.name}</a>
    </li>
</g:each>

